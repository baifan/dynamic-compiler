package tech.weiyi.dynamic.bytecode.analysis;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static tech.weiyi.dynamic.bytecode.analysis.AnalyzerUtil.clazzDescToName;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofField;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofInterface;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofInvokeDynamic;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofLdc;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofMethod;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofMethodArgument;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofMethodCatchException;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofMethodLocalVariable;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofNewArray;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofNewMultiArray;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofSuper;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofType;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementFactory.ofTypeNewArray;
import static tech.weiyi.dynamic.bytecode.analysis.TypeCst.TYPE_ARRAY_DESCS;
import static tech.weiyi.dynamic.bytecode.analysis.TypeCst.TYPE_ARRAY_NAMES;

public class ClazzCallAnalyzer {

    public static Set<ClazzElement> getUsedClassSet(byte[] classBytes) {
        ClassReader classReader = new ClassReader(classBytes);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, ClassReader.SKIP_DEBUG);
        Set<ClazzElement> classSet = new HashSet<>();
        if (classNode.superName != null && !"java/lang/Object".equals(classNode.superName)) {
            classSet.add(ofSuper(clazzDescToName(classNode.superName), classNode.name));
        }
        if (classNode.interfaces != null) {
            for (String anInterface : classNode.interfaces) {
                classSet.add(ofInterface(clazzDescToName(anInterface), classNode.name));
            }
        }
        classSet.addAll(getClassInFields(classNode));
        classSet.addAll(getClassFromMethod(classNode));
        return classSet;
    }

    private static Set<ClazzElement> getClassInFields(ClassNode classNode) {
        Set<ClazzElement> result = new HashSet<>();
        List<FieldNode> fieldList = classNode.fields;
        for (FieldNode fieldNode : fieldList) {
            String fieldTypeName = Type.getType(fieldNode.desc).getClassName();
            result.add(ofField(fieldTypeName, fieldNode.desc));
        }
        return result;
    }

    private static Set<ClazzElement> getClassFromMethod(ClassNode classNode) {
        Set<ClazzElement> result = new HashSet<>();
        List<MethodNode> methodList = classNode.methods;
        for (MethodNode methodNode : methodList) {
            for (Type argumentType : Type.getArgumentTypes(methodNode.desc)) {
                result.add(ofMethodArgument(argumentType.getClassName(), argumentType.getDescriptor()));
            }

            List<LocalVariableNode> lvNodeList = methodNode.localVariables;
            for (LocalVariableNode lvn : lvNodeList) {
                result.add(ofMethodLocalVariable(Type.getType(lvn.desc).getClassName(), lvn.desc));
            }

            List<TryCatchBlockNode> tryCatchBlockNodes = methodNode.tryCatchBlocks;
            if (tryCatchBlockNodes != null) {
                for (TryCatchBlockNode tryCatchBlockNode : tryCatchBlockNodes) {
                    result.add(ofMethodCatchException(tryCatchBlockNode.type, tryCatchBlockNode.type));
                }
            }
            result.addAll(getClassMethodInner(methodNode));
        }
        return result;
    }

    private static Set<ClazzElement> getClassMethodInner(MethodNode methodNode) {
        Set<ClazzElement> result = new HashSet<>();
        Iterator<AbstractInsnNode> itr = methodNode.instructions.iterator(0);
        while (itr.hasNext()) {
            AbstractInsnNode insn = itr.next();
            switch (insn.getType()) {
                case AbstractInsnNode.INT_INSN:
                    IntInsnNode intInsn = (IntInsnNode) insn;
                    if (insn.getOpcode() == Opcodes.NEWARRAY) {
                        result.add(ofNewArray(TYPE_ARRAY_NAMES[intInsn.operand], TYPE_ARRAY_DESCS[intInsn.operand]));
                    }
                    break;
                case AbstractInsnNode.FIELD_INSN:
                    FieldInsnNode fieldInsn = ((FieldInsnNode) insn);
                    result.add(ofField(clazzDescToName(fieldInsn.owner), fieldInsn.desc));
                    break;
                case AbstractInsnNode.METHOD_INSN:
                    MethodInsnNode methodInsn = ((MethodInsnNode) insn);
                    result.add(ofMethod(clazzDescToName(methodInsn.owner), methodInsn.name, methodInsn.desc));
                    break;
                case AbstractInsnNode.TYPE_INSN:
                    String typeInsnDesc = ((TypeInsnNode) insn).desc;
                    if (insn.getOpcode() == Opcodes.ANEWARRAY) {
                        result.add(ofTypeNewArray(clazzDescToName(typeInsnDesc), typeInsnDesc));
                    } else {
                        result.add(ofType(clazzDescToName(typeInsnDesc), typeInsnDesc));
                    }
                    break;
                case AbstractInsnNode.INVOKE_DYNAMIC_INSN:
                    InvokeDynamicInsnNode dynamicInsn = ((InvokeDynamicInsnNode) insn);
                    if (dynamicInsn.bsm != null && dynamicInsn.bsm instanceof Handle) {
                        Handle bsmHandle = dynamicInsn.bsm;
                        result.add(ofInvokeDynamic(clazzDescToName(bsmHandle.getOwner()), bsmHandle.getName(), bsmHandle.getDesc()));
                    }
                    if (dynamicInsn.bsmArgs != null) {
                        for (Object bsmArg : dynamicInsn.bsmArgs) {
                            if (bsmArg instanceof Handle) {
                                Handle argHandler = (Handle) bsmArg;
                                result.add(ofMethod(clazzDescToName(argHandler.getOwner()), argHandler.getName(), argHandler.getDesc()));
                            }
                        }
                    }
                    break;
                case AbstractInsnNode.LDC_INSN:
                    Object cst = ((LdcInsnNode) insn).cst;
                    if (cst instanceof Type) {
                        result.add(ofLdc(((Type) cst).getClassName(), ((Type) cst).getDescriptor()));
                    }
                    break;
                case AbstractInsnNode.MULTIANEWARRAY_INSN:
                    MultiANewArrayInsnNode multiANewArrayInsn = (MultiANewArrayInsnNode) insn;
                    result.add(ofNewMultiArray(clazzDescToName(multiANewArrayInsn.desc), multiANewArrayInsn.desc));
                    break;
                default:
                    break;
            }
        }
        return result;
    }

}
