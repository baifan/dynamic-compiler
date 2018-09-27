package tech.weiyi.dynamic.bytecode.compiler;

import java.util.Map;

public class DynamicClassLoader extends ClassLoader {

    private Map<String, MemoryClazzFile> memoryClassFileMap;

    public DynamicClassLoader(ClassLoader parent, Map<String, MemoryClazzFile> memoryClassFileMap) {
        super(parent);
        this.memoryClassFileMap = memoryClassFileMap;
    }

    protected Class<?> findClass(String name) {
        byte[] b = this.memoryClassFileMap.get(name).getBytes();
        return defineClass(name, b, 0, b.length);
    }

}
