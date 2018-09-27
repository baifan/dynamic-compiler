package tech.weiyi.dynamic.bytecode.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

public class MemoryClazzFile extends SimpleJavaFileObject {

    private ByteArrayOutputStream classBytes;

    protected MemoryClazzFile(String name) {
        super(URI.create("string:///" + name.replaceAll("\\.", "/") + Kind.CLASS.extension), Kind.CLASS);
        this.classBytes = new ByteArrayOutputStream();
    }

    public byte[] getBytes() {
        return this.classBytes.toByteArray();
    }

    public OutputStream openOutputStream() {
        return classBytes;
    }
}
