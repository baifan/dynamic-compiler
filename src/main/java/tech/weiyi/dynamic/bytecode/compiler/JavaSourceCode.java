package tech.weiyi.dynamic.bytecode.compiler;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class JavaSourceCode extends SimpleJavaFileObject {

    private String clazzName;

    private String sourceCode;

    protected JavaSourceCode(String clazzName, String sourceCode) {
        super(URI.create("string:///" + clazzName.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.clazzName = clazzName;
        this.sourceCode = sourceCode;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return this.sourceCode;
    }

    public String getName() {
        return clazzName;
    }

}
