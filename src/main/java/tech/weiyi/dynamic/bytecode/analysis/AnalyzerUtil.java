package tech.weiyi.dynamic.bytecode.analysis;

import org.objectweb.asm.Type;

import java.util.HashSet;
import java.util.Set;

public class AnalyzerUtil {

    public static final String clazzDescToName(String clazzDesc) {
        if (clazzDesc.charAt(0) == '[') {
            return Type.getType(clazzDesc).getElementType().getClassName();
        }
        return clazzDesc.replace('/', '.');
    }

    public static Set<String> getAllClazzesFromMethodDesc(String methodDesc) {
        Set<String> clazzSet = new HashSet<>();
        Type returnType = Type.getReturnType(methodDesc);
        clazzSet.add(returnType.getClassName());
        Type[] argTypes = Type.getArgumentTypes(methodDesc);
        if (argTypes == null || argTypes.length == 0) {
            return clazzSet;
        }
        for (Type argType : argTypes) {
            clazzSet.add(argType.getClassName());
        }
        return clazzSet;
    }
}
