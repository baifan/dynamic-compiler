package tech.weiyi.dynamic.bytecode.analysis;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static tech.weiyi.dynamic.bytecode.analysis.AnalyzerUtil.clazzDescToName;

public class MethodCallAnalyzer {

    public static Set<MethodCallElement> getUsedClassSet(byte[] classBytes) {
        ClassReader classReader = new ClassReader(classBytes);
        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, ClassReader.SKIP_DEBUG);
        return getClassFromMethod(classNode);
    }

    private static Set<MethodCallElement> getClassFromMethod(ClassNode classNode) {
        Set<MethodCallElement> result = new HashSet<>();
        List<MethodNode> methodList = classNode.methods;
        for (MethodNode methodNode : methodList) {
            result.addAll(getClassMethodInner(methodNode));
        }
        return result;
    }

    private static Set<MethodCallElement> getClassMethodInner(MethodNode methodNode) {
        Set<MethodCallElement> result = new HashSet<>();
        Iterator<AbstractInsnNode> itr = methodNode.instructions.iterator(0);
        while (itr.hasNext()) {
            AbstractInsnNode insn = itr.next();
            switch (insn.getType()) {
                case AbstractInsnNode.FIELD_INSN:
                    FieldInsnNode fieldInsn = ((FieldInsnNode) insn);
                    result.add(new MethodCallElement(insn.getType(), clazzDescToName(fieldInsn.owner), fieldInsn.name, fieldInsn.desc));
                    break;
                case AbstractInsnNode.METHOD_INSN:
                    MethodInsnNode methodInsn = ((MethodInsnNode) insn);
                    result.add(new MethodCallElement(insn.getType(), clazzDescToName(methodInsn.owner), methodInsn.name, methodInsn.desc));
                    break;
                case AbstractInsnNode.INVOKE_DYNAMIC_INSN:
                    InvokeDynamicInsnNode dynamicInsnDesc = ((InvokeDynamicInsnNode) insn);
                    result.add(new MethodCallElement(insn.getType(), Type.getReturnType(dynamicInsnDesc.desc).getClassName(), dynamicInsnDesc.name, dynamicInsnDesc.desc));
                    break;
                case AbstractInsnNode.LDC_INSN:
                    LdcInsnNode ldcInsnNode = (LdcInsnNode) insn;
                    if (ldcInsnNode.cst instanceof Type) {
                        result.add(new MethodCallElement(insn.getType(), ((Type) ldcInsnNode.cst).getClassName(), "class", ((Type) ldcInsnNode.cst).getDescriptor()));
                    }
                    break;
                default:
                    break;
            }
        }
        return result;
    }

}
