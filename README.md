# Dynamic Compile Java && Check byte code

[![Build Status](https://travis-ci.org/baifan/dynamic-compiler.svg?branch=master)](https://travis-ci.org/baifan/dynamic-compiler)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)



1. Dynamic compile java source file to byte codes.
2. Check which classes and methods are called in the byte codes. 

## Project Usage

### Build

To build the project:

```bash
$ git clone git@github.com:baifan/dynamic-compiler.git
$ cd dynamic-compiler
## if you already has `mvn` in path, only use this cmd
## $ mvn compile package install
## if you doesn't has `mvn` in path, then use this cmd
$ ./mvnw compile package install

```

### Binaries

This project does't upload to Maven Central.
If you want to use it, you can install to local repository.

```xml
<dependency>
  <groupId>tech.weyi.dynamic.compiler</groupId>
  <artifactId>dynamic-compiler</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Method

**Compile java source**

```java
import tech.weiyi.dynamic.bytecode.compiler.DynamicCompiler;
import tech.weiyi.dynamic.bytecode.compiler.MemoryClazzFile;


import java.util.Map;


class TestCompile {
    public void testCompile(String clazzName, String clazzSource) {
        Map<String, MemoryClazzFile> byteClazzMap = DynamicCompiler.compile(clazzName, clazzSource);
    }
}
```

**Analysis clazz element**

```java
import tech.weiyi.dynamic.bytecode.analysis.ClazzCallAnalyzer;
import tech.weiyi.dynamic.bytecode.analysis.ClazzElement;
import tech.weiyi.dynamic.bytecode.compiler.MemoryClazzFile;

import java.util.Map;
import java.util.Set;

class TestAnalysisClazz {
    public void testAnalysisClazz(String clazzName, Map<String, MemoryClazzFile> memClsMap) {
        MemoryClazzFile memoryClazzFile = memClsMap.get(clazzName);
        Set<ClazzElement> clazzElements = ClazzCallAnalyzer.getUsedClassSet(memoryClazzFile.getBytes());
    }
}
```

**Analysis method element**

```java
import tech.weiyi.dynamic.bytecode.analysis.MethodCallAnalyzer;
import tech.weiyi.dynamic.bytecode.analysis.MethodCallElement;
import tech.weiyi.dynamic.bytecode.compiler.MemoryClazzFile;

import java.util.Map;
import java.util.Set;

class TestAnalysisMethod {
    public void testAnalysisMethod(String clazzName, Map<String, MemoryClazzFile> memClsMap) {
        MemoryClazzFile memoryClazzFile = memClsMap.get(clazzName);
        Set<MethodCallElement> methodCallElements = MethodCallAnalyzer.getUsedClassSet(memoryClazzFile.getBytes());
    }
}
```
