package tech.weiyi.dynamic.bytecode.compiler;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicCompiler {

    public static Map<String, MemoryClazzFile> compile(String clazzFullName, String sourceCode) {
        JavaSourceCode source = new JavaSourceCode(clazzFullName, sourceCode);
        List<JavaSourceCode> sourceCodes = Arrays.asList(source);
        List<String> options = Arrays.asList("-classpath", CompilePathUtil.getCompileClassPath());
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaFileManager fileManager = null;
        Map<String, MemoryClazzFile> memoryClassFileMap = new HashMap<>();
        try {
            fileManager = new MemoryClazzFileManager(compiler.getStandardFileManager(null, null, null), memoryClassFileMap);
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StringWriter out = new StringWriter();
            CompilationTask task = compiler.getTask(out, fileManager, diagnostics, options, null, sourceCodes);
            boolean success = task.call();
            if (!success) {
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                    out.append("Compile failed at line num: " + diagnostic.getLineNumber() + " in " + diagnostic).append('\n');
                }
                throw new IllegalArgumentException(out.toString());
            }
            return memoryClassFileMap;
        } finally {
            try {
                fileManager.close();
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static Class<?> loadClass(String clazzFullName, Map<String, MemoryClazzFile> memoryClassFileMap) {
        DynamicClassLoader loader = new DynamicClassLoader(ClassLoader.getSystemClassLoader(), memoryClassFileMap);
        Class<?> clazz;
        try {
            clazz = loader.loadClass(clazzFullName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return clazz;
    }
}
