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

    private static final String clazzName = "Hello";

    private Map<String, MemoryClazzFile> getMemoryClassMap() throws Exception {
        InputStream is = TestHotCompileUtil.class.getResourceAsStream("/test/Hello.java");
        int fileSize = is.available();
        byte[] bytes = new byte[fileSize];
        if (is.read(bytes) != fileSize) {
            System.out.println("未完全读取数据！");
            return Collections.emptyMap();
        }

        String source = new String(bytes);
        String clazzName = "Hello";
        return DynamicCompiler.compile(clazzName, source);
    }

    private byte[] getClassByte() throws Exception {
        InputStream is = TestHotCompileUtil.class.getResourceAsStream("/tech/weiyi/dynamic/bytecode/analysis/ClazzCallAnalyzer.class");
        int fileSize = is.available();
        byte[] bytes = new byte[fileSize];
        if (is.read(bytes) != fileSize) {
            System.out.println("未完全读取数据！");
            throw new IllegalArgumentException();
        }
        return bytes;
    }

    @Test
    public void analysisClazzDefine() throws Exception {
        Map<String, MemoryClazzFile> memoryClazzFileMap = getMemoryClassMap();
        System.out.println("==================================================");
        System.out.println("Class Size:" + memoryClazzFileMap.size());
        for (Map.Entry<String, MemoryClazzFile> entry : memoryClazzFileMap.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("==================================================");

        MemoryClazzFile memoryClazzFile = memoryClazzFileMap.get(clazzName);
        MethodCallAnalyzer.getUsedClassSet(memoryClazzFile.getBytes());
        Class clazz = DynamicCompiler.loadClass(clazzName, memoryClazzFileMap);
        System.out.println(clazz.getTypeName());
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + "\t\t" + method.isSynthetic());
        }

    }

    @Test
    public void analysisMethodCall() throws Exception {
        Map<String, MemoryClazzFile> memoryClazzFileMap = getMemoryClassMap();
        MemoryClazzFile memoryClazzFile = memoryClazzFileMap.get(clazzName);
        Set<MethodCallElement> classSet = MethodCallAnalyzer.getUsedClassSet(memoryClazzFile.getBytes());
        System.out.println("==================================================");
        System.out.println(JSONObject.toJSONString(classSet, true));
        System.out.println("==================================================");

    }

    @Test
    public void analysisClazzElement() throws Exception {
        Map<String, MemoryClazzFile> memoryClazzFileMap = getMemoryClassMap();
        MemoryClazzFile memoryClazzFile = memoryClazzFileMap.get(clazzName);
        Set<ClazzElement> clazzElements = ClazzCallAnalyzer.getUsedClassSet(memoryClazzFile.getBytes());
        System.out.println("==================================================");
        System.out.println(JSONObject.toJSONString(clazzElements, true));
        System.out.println("==================================================");
    }

    @Test
    public void analysisClazzElementBytes() throws Exception {
        Set<ClazzElement> clazzElements = ClazzCallAnalyzer.getUsedClassSet(getClassByte());
        System.out.println("==================================================");
        System.out.println(JSONObject.toJSONString(clazzElements, true));
        System.out.println("==================================================");
    }
}
