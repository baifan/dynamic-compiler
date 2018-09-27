package tech.weiyi.dynamic.bytecode.compiler;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardJavaFileManager;
import java.io.IOException;
import java.util.Map;

public class MemoryClazzFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {

    private Map<String, MemoryClazzFile> memoryClazzFileMap;

    protected MemoryClazzFileManager(StandardJavaFileManager fileManager, Map<String, MemoryClazzFile> memoryClazzFileMap) {
        super(fileManager);
        this.memoryClazzFileMap = memoryClazzFileMap;
    }

    public JavaFileObject getJavaFileForOutput(Location location, String clazzFullName, Kind kind, FileObject sibling) {
        if (memoryClazzFileMap.containsKey(clazzFullName)) {
            return memoryClazzFileMap.get(clazzFullName);
        } else {
            MemoryClazzFile file = new MemoryClazzFile(clazzFullName);
            this.memoryClazzFileMap.put(clazzFullName, file);
            return file;
        }
    }

    public void close() throws IOException {
        super.close();
        this.memoryClazzFileMap = null;
    }

}
