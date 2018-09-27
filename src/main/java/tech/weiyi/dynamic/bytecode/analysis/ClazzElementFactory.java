package tech.weiyi.dynamic.bytecode.analysis;

import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.FIELD_INSN;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.INTERFACE;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.INT_INSN;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.INVOKE_DYNAMIC_INSN;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.LDC_TYPE;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.METHOD_ARGUMENT;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.METHOD_CATCH_EXCEPTION;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.METHOD_INSN;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.METHOD_LOCAL_VARIABLE;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.MULTI_A_NEW_ARRAY;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.SUPER;
import static tech.weiyi.dynamic.bytecode.analysis.ClazzElementType.TYPE_INSN;

public class ClazzElementFactory {


    /**
     * 私有构造函数
     */
    private ClazzElementFactory() {
        super();
    }

    public static ClazzElement ofSuper(String clazz, String desc) {
        return new ClazzElement(clazz, SUPER.id, SUPER.name, desc);
    }

    public static ClazzElement ofInterface(String clazz, String desc) {
        return new ClazzElement(clazz, INTERFACE.id, INTERFACE.name, desc);
    }

    public static ClazzElement ofNewArray(String clazz, String desc) {
        return new ClazzElement(clazz, INT_INSN.id, INT_INSN.name, desc);
    }

    public static ClazzElement ofType(String clazz, String desc) {
        return new ClazzElement(clazz, TYPE_INSN.id, TYPE_INSN.name, desc);
    }

    public static ClazzElement ofTypeNewArray(String clazz, String desc) {
        return new ClazzElement(clazz, TYPE_INSN.id, clazz + "[]", "[" + desc);
    }

    public static ClazzElement ofField(String clazz, String desc) {
        return new ClazzElement(clazz, FIELD_INSN.id, FIELD_INSN.name, desc);
    }

    public static ClazzElement ofMethod(String clazz, String name, String desc) {
        return new ClazzElement(clazz, METHOD_INSN.id, name, desc);
    }

    public static ClazzElement ofMethodArgument(String clazz, String desc) {
        return new ClazzElement(clazz, METHOD_ARGUMENT.id, METHOD_ARGUMENT.name, desc);
    }

    public static ClazzElement ofMethodLocalVariable(String clazz, String desc) {
        return new ClazzElement(clazz, METHOD_LOCAL_VARIABLE.id, METHOD_LOCAL_VARIABLE.name, desc);
    }

    public static ClazzElement ofMethodCatchException(String clazz, String desc) {
        return new ClazzElement(clazz, METHOD_CATCH_EXCEPTION.id, METHOD_CATCH_EXCEPTION.name, desc);
    }

    public static ClazzElement ofInvokeDynamic(String clazz, String name, String desc) {
        return new ClazzElement(clazz, INVOKE_DYNAMIC_INSN.id, name, desc);
    }

    public static ClazzElement ofLdc(String clazz, String desc) {
        return new ClazzElement(clazz, LDC_TYPE.id, LDC_TYPE.name, desc);
    }

    public static ClazzElement ofNewMultiArray(String clazz, String desc) {
        return new ClazzElement(clazz, MULTI_A_NEW_ARRAY.id, MULTI_A_NEW_ARRAY.name, desc);
    }

}
