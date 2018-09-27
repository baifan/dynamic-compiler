package tech.weiyi.dynamic.bytecode.checker;

import tech.weiyi.dynamic.bytecode.analysis.MethodCallAnalyzer;
import tech.weiyi.dynamic.bytecode.analysis.MethodCallElement;

import java.util.Map;
import java.util.Set;

public class DefaultMethodCallChecker implements MethodCallChecker {


    private Set<String> supportClazzes;

    private Set<String> supportMethods;

    /**
     * 公共构造函数
     */
    public DefaultMethodCallChecker() {
        super();
    }

    private static String getMethodIdentifier(MethodCallElement methodCallElement) {
        return methodCallElement.getOwner() + "#" + methodCallElement.getCallName();
    }

    @Override
    public MethodCheckerResult check(Map<String, byte[]> clazzByteCodeMap) {
        for (Map.Entry<String, byte[]> entry : clazzByteCodeMap.entrySet()) {
            MethodCheckerResult methodCheckerResult = check(clazzByteCodeMap.keySet(), entry.getValue());
            if (!methodCheckerResult.getSuccess()) {
                return methodCheckerResult;
            }
        }
        MethodCheckerResult checkerResult = new MethodCheckerResult();
        checkerResult.setSuccess(true);
        return checkerResult;
    }

    private MethodCheckerResult check(Set<String> generatedClass, byte[] clazzByteCode) {
        Set<MethodCallElement> methodCallElements = MethodCallAnalyzer.getUsedClassSet(clazzByteCode);
        boolean success = true;
        MethodCheckerResult methodCheckerResult = new MethodCheckerResult();
        for (MethodCallElement methodCallElement : methodCallElements) {
            if (generatedClass.contains(methodCallElement.getOwner())
                    || supportClazzes.contains(methodCallElement.getOwner())
                    || supportMethods.contains(getMethodIdentifier(methodCallElement))) {
            } else {
                methodCheckerResult.addUnsupportMethod(methodCallElement);
                success = false;
            }

        }
        methodCheckerResult.setSuccess(success);
        return methodCheckerResult;
    }

    public Set<String> getSupportClazzes() {
        return supportClazzes;
    }

    public void setSupportClazzes(Set<String> supportClazzes) {
        this.supportClazzes = supportClazzes;
    }

    public Set<String> getSupportMethods() {
        return supportMethods;
    }

    public void setSupportMethods(Set<String> supportMethods) {
        this.supportMethods = supportMethods;
    }
}
