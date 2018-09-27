package tech.weiyi.dynamic.bytecode.compiler.asm;

import org.objectweb.asm.Type;

public class TestType {

    public static void main(String[] args) {
        String description = "(Ljava.util.Map;Ljava.lang.Long;)Ljava.util.function.BiConsumer;";
        Type type = Type.getReturnType(description);
        System.out.println(type.getClassName());
        Type[] argumentTypes = Type.getArgumentTypes(description);

        for (Type argumentType : argumentTypes) {
            System.out.println(argumentType.getClassName());
        }

    }

}
