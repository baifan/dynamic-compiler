package tech.weiyi.dynamic.bytecode.compiler.hot;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import tech.weiyi.dynamic.bytecode.analysis.ClazzCallAnalyzer;
import tech.weiyi.dynamic.bytecode.analysis.ClazzElement;
import tech.weiyi.dynamic.bytecode.analysis.MethodCallAnalyzer;
import tech.weiyi.dynamic.bytecode.analysis.MethodCallElement;
import tech.weiyi.dynamic.bytecode.compiler.DynamicCompiler;
import tech.weiyi.dynamic.bytecode.compiler.MemoryClazzFile;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TestHotCompileUtil {

    private static final String className = "Hello";

    private Map<String, MemoryClazzFile> getMemoryClassMap() throws Exception {
        InputStream is = TestHotCompileUtil.class.getResourceAsStream("/test/Hello.java");
        int fileSize = is.available();
        byte[] bytes = new byte[fileSize];
        if (is.read(bytes) != fileSize) {
            System.out.println("未完全读取数据！");
            return Collections.emptyMap();
        }

        String source = new String(bytes);

        String className = "Hello";

        return DynamicCompiler.compile(className, source);


    }

    private byte[] getClassByte() throws Exception {
        InputStream is = TestHotCompileUtil.class.getResourceAsStream("/test/TestByteCode.class");
        int fileSize = is.available();
        byte[] bytes = new byte[fileSize];
        if (is.read(bytes) != fileSize) {
            System.out.println("未完全读取数据！");
            throw new IllegalArgumentException();
        }
        return bytes;
    }

    @Test
    public void analysisClassDefine() throws Exception {
        Map<String, MemoryClazzFile> memClsMap = getMemoryClassMap();
        System.out.println("==================================================");
        System.out.println("Class Size:" + memClsMap.size());
        for (Map.Entry<String, MemoryClazzFile> entry : memClsMap.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("==================================================");

        MemoryClazzFile memoryClassFile = memClsMap.get(className);

        MethodCallAnalyzer.getUsedClassSet(memoryClassFile.getBytes());

        Class clazz = DynamicCompiler.loadClass(className, memClsMap);
        System.out.println(clazz.getTypeName());
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "\t\t" + method.isSynthetic());
        }

    }

    @Test
    public void analysisMethodCall() throws Exception {
        Map<String, MemoryClazzFile> memClsMap = getMemoryClassMap();
        MemoryClazzFile memoryClassFile = memClsMap.get(className);
        Set<MethodCallElement> classSet = MethodCallAnalyzer.getUsedClassSet(memoryClassFile.getBytes());
        System.out.println("==================================================");
        System.out.println(JSONObject.toJSONString(classSet, true));
        System.out.println("==================================================");

    }

    @Test
    public void analysisClassElement() throws Exception {
        Map<String, MemoryClazzFile> memClsMap = getMemoryClassMap();
        MemoryClazzFile memoryClassFile = memClsMap.get(className);
        Set<ClazzElement> clazzElements = ClazzCallAnalyzer.getUsedClassSet(memoryClassFile.getBytes());
        System.out.println("==================================================");
        System.out.println(JSONObject.toJSONString(clazzElements, true));
        System.out.println("==================================================");
    }

    @Test
    public void analysisClassElementBytes() throws Exception {
        Set<ClazzElement> clazzElements = ClazzCallAnalyzer.getUsedClassSet(getClassByte());
        System.out.println("==================================================");
        System.out.println(JSONObject.toJSONString(clazzElements, true));
        System.out.println("==================================================");
    }
}
