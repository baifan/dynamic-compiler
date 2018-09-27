package tech.weiyi.dynamic.bytecode.compiler;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

public final class CompilePathUtil {

    private static volatile String compilePath = null;

    /**
     * 私有构造函数
     */
    private CompilePathUtil() {
        super();
    }

    public static String getCompileClassPath() {
        if (compilePath != null) {
            return compilePath;
        }
        Set<String> pathSet = new HashSet<>();
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        if (loader instanceof URLClassLoader) {
            for (URL url : ((URLClassLoader) loader).getURLs()) {
                String path = url.getPath();
                if (path != null && !path.isEmpty() &&
                        (path.endsWith(File.separatorChar + "rt.jar")
                                || path.endsWith(File.separatorChar + "charsets.jar")
                                || path.contains(File.separatorChar + "fastjson-")
                                || path.contains(File.separatorChar + "commons-lang3-")
                                || path.contains(File.separatorChar + "commons-codec-")
                                || path.contains(File.separatorChar + "commons-io-")
                                || path.contains(File.separatorChar + "guava-")
                        )
                ) {
                    pathSet.add(path);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (String token : pathSet) {
            result.append(token).append(File.pathSeparatorChar);
        }
        compilePath = result.toString();
        return compilePath;
    }
}
