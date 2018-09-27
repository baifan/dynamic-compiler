package tech.weiyi.dynamic.bytecode.checker;

import java.util.Map;

public interface MethodCallChecker {

    MethodCheckerResult check(Map<String, byte[]> clazzByteCodeMap);
}
