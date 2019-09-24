package com.ddf.annotation.compiler;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * Java 中的编译时注解：https://segmentfault.com/a/1190000009756015
 *
 * 运行下面两个命令生产Player_New.java文件
 * javac -encoding UTF-8 -d out/production/classes src/main/java/com/ddf/annotation/compiler/HelloProcessor.java src/main/java/com/ddf/annotation/compiler/Hello.java
 *
 * javac -encoding UTF-8 -cp out/production/classes -processor com.ddf.annotation.compiler.HelloProcessor -d out/production/classes -s out/src/ src/main/java/com/ddf/annotation/compiler/*.java
 */

@SupportedSourceVersion(SourceVersion.RELEASE_8) // 源码级别, 这里的环境是 jdk 1.8
@SupportedAnnotationTypes("com.ddf.annotation.compiler.Hello") // 处理的注解类型, 这里需要处理的是 apt 包下的 Hello 注解(这里也可以不用注解, 改成重写父类中对应的两个方法)
public class HelloProcessor extends AbstractProcessor {

    // 计数器, 用于计算 process() 方法运行了几次
    private int count = 1;

    // 用于写文件
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }


    // 处理编译时注解的方法
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("start process, count = " + count++);
        // 获得所有类
        Set<? extends Element> rootElements = roundEnv.getRootElements();
        System.out.println("all class:");

        for (Element rootElement : rootElements) {
            System.out.println("  " + rootElement.getSimpleName());
        }

        // 获得有注解的元素, 这里 Hello 只能修饰类, 所以只有类
        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Hello.class);
        System.out.println("annotated class:");
        for (Element element : elementsAnnotatedWith) {
            String className = element.getSimpleName().toString();
            System.out.println("  " + className);

            String output = element.getAnnotation(Hello.class).name();
            // 产生的动态类的名字
            String newClassName = className + "_New";
            // 写 java 文件
            createFile(newClassName, output);
        }
        return true;
    }

    private void createFile(String className, String output) {
        StringBuilder cls = new StringBuilder();
        cls.append("package com.ddf.annotation.compiler;\n\npublic class ")
                .append(className)
                .append(" {\n  public static void main(String[] args) {\n")
                .append("    System.out.println(\"")
                .append(output)
                .append("\");\n  }\n}");
        try {
            JavaFileObject sourceFile = filer.createSourceFile("com.ddf.annotation.compiler." + className);
            Writer writer = sourceFile.openWriter();
            writer.write(cls.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
