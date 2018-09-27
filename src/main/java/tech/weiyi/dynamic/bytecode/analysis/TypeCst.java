package tech.weiyi.dynamic.bytecode.analysis;

public class TypeCst {

    /**
     * The names of the {@code operand} values of the {@link
     * org.objectweb.asm.MethodVisitor#visitIntInsn} method when {@code opcode} is {@code NEWARRAY}.
     */
    public static final String[] TYPE_NAMES = {
            "",
            "",
            "",
            "",
            /**
             * 4
             * {@link org.objectweb.asm.Opcodes#T_BOOLEAN}
            */
            "boolean",
            /**
             * 5
             * {@link org.objectweb.asm.Opcodes#T_CHAR}
            */
            "char",
            /**
             * 6
             * {@link org.objectweb.asm.Opcodes#T_FLOAT}
            */
            "float",
            /**
             * 7
             * {@link org.objectweb.asm.Opcodes#T_DOUBLE}
            */
            "double",
            /**
             * 8
             * {@link org.objectweb.asm.Opcodes#T_BYTE}
            */
            "byte",
            /**
             * 9
             * {@link org.objectweb.asm.Opcodes#T_SHORT}
            */
            "short",
            /**
             * 10
             * {@link org.objectweb.asm.Opcodes#T_INT}
            */
            "int",
            /**
             * 11
             * {@link org.objectweb.asm.Opcodes#T_LONG}
            */
            "long"
    };

    /**
     * The names of the {@code operand} values of the {@link
     * org.objectweb.asm.MethodVisitor#visitIntInsn} method when {@code opcode} is {@code NEWARRAY}.
     */
    public static final String[] TYPE_ARRAY_NAMES = {
            "",
            "",
            "",
            "",
            /**
             * 4
             * {@link org.objectweb.asm.Opcodes#T_BOOLEAN}
            */
            "boolean[]",
            /**
             * 5
             * {@link org.objectweb.asm.Opcodes#T_CHAR}
            */
            "char[]",
            /**
             * 6
             * {@link org.objectweb.asm.Opcodes#T_FLOAT}
            */
            "float[]",
            /**
             * 7
             * {@link org.objectweb.asm.Opcodes#T_DOUBLE}
            */
            "double[]",
            /**
             * 8
             * {@link org.objectweb.asm.Opcodes#T_BYTE}
            */
            "byte[]",
            /**
             * 9
             * {@link org.objectweb.asm.Opcodes#T_SHORT}
            */
            "short[]",
            /**
             * 10
             * {@link org.objectweb.asm.Opcodes#T_INT}
            */
            "int[]",
            /**
             * 11
             * {@link org.objectweb.asm.Opcodes#T_LONG}
            */
            "long[]"
    };

    public static final String[] TYPE_DESCS = {
            "",
            "",
            "",
            "",
            /**
             * 4
             * {@link org.objectweb.asm.Opcodes#T_BOOLEAN}
            */
            "Z",
            /**
             * 5
             * {@link org.objectweb.asm.Opcodes#T_CHAR}
            */
            "C",
            /**
             * 6
             * {@link org.objectweb.asm.Opcodes#T_FLOAT}
            */
            "F",
            /**
             * 7
             * {@link org.objectweb.asm.Opcodes#T_DOUBLE}
            */
            "D",
            /**
             * 8
             * {@link org.objectweb.asm.Opcodes#T_BYTE}
            */
            "B",
            /**
             * 9
             * {@link org.objectweb.asm.Opcodes#T_SHORT}
            */
            "S",
            /**
             * 10
             * {@link org.objectweb.asm.Opcodes#T_INT}
            */
            "I",
            /**
             * 11
             * {@link org.objectweb.asm.Opcodes#T_LONG}
            */
            "J"
    };

    public static final String[] TYPE_ARRAY_DESCS = {
            "",
            "",
            "",
            "",
            /**
             * 4
             * {@link org.objectweb.asm.Opcodes#T_BOOLEAN}
            */
            "[Z",
            /**
             * 5
             * {@link org.objectweb.asm.Opcodes#T_CHAR}
            */
            "[C",
            /**
             * 6
             * {@link org.objectweb.asm.Opcodes#T_FLOAT}
            */
            "[F",
            /**
             * 7
             * {@link org.objectweb.asm.Opcodes#T_DOUBLE}
            */
            "[D",
            /**
             * 8
             * {@link org.objectweb.asm.Opcodes#T_BYTE}
            */
            "[B",
            /**
             * 9
             * {@link org.objectweb.asm.Opcodes#T_SHORT}
            */
            "[S",
            /**
             * 10
             * {@link org.objectweb.asm.Opcodes#T_INT}
            */
            "[I",
            /**
             * 11
             * {@link org.objectweb.asm.Opcodes#T_LONG}
            */
            "[J"
    };

}
