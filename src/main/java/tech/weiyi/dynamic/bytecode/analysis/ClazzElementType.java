package tech.weiyi.dynamic.bytecode.analysis;

/**
 * Clazz元素的类型
 */
public enum ClazzElementType {

    /**
     * Super
     */
    SUPER(100, "super"),

    /**
     * Interface
     */
    INTERFACE(101, "interface"),

    /**
     * Method argument
     */
    METHOD_ARGUMENT(102, "methodArgument"),

    /**
     * Method Local Variable
     */
    METHOD_LOCAL_VARIABLE(103, "methodLocalVariable"),

    /**
     * Method try catch exception
     */
    METHOD_CATCH_EXCEPTION(103, "methoCatchException"),

    /**
     * 元素使用
     * NEWARRAY
     *
     * @see org.objectweb.asm.tree.IntInsnNode
     */
    INT_INSN(1, "newArray"),

    /**
     * 元素使用
     * ANEWARRAY, CHECKCAST or INSTANCEOF
     *
     * @see org.objectweb.asm.tree.TypeInsnNode
     */
    TYPE_INSN(3, "type"),

    /**
     * 属性使用
     * GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
     *
     * @see org.objectweb.asm.tree.FieldInsnNode
     */
    FIELD_INSN(4, "field"),

    /**
     * 方法调用
     *
     * @see org.objectweb.asm.tree.MethodInsnNode
     */
    METHOD_INSN(5, "method"),

    /**
     * 动态调用
     * <p>
     * java lambda
     *
     * @see org.objectweb.asm.tree.InvokeDynamicInsnNode
     */
    INVOKE_DYNAMIC_INSN(6, "invokeDynamic"),


    /**
     * Thread.class.getName();
     *
     * @see org.objectweb.asm.tree.LdcInsnNode
     */
    LDC_TYPE(9, "ldc"),


    /**
     * Multi ANew Array;
     *
     * @see org.objectweb.asm.tree.MultiANewArrayInsnNode
     */
    MULTI_A_NEW_ARRAY(13, "multiANewArrayIns"),

    ;

    public final int id;

    public final String name;

    ClazzElementType(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
