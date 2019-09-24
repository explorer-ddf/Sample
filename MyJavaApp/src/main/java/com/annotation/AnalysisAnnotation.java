package com.annotation;

import java.lang.reflect.Method;

/**
 * Created by ddf on 16/2/2.
 *
 * https://segmentfault.com/a/1190000009573386
 *
 * http://blog.csdn.net/lylwo317/article/details/52163304
 *
 * http://blog.csdn.net/briblue/article/details/73824058
 *
 * 这里使用反射获取注解信息. 只有标注为 RetentionPolicy.RUNTIME 的注解可以这么提取信息. 运行时注解解析器
 */
public class AnalysisAnnotation {
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        try {

            // 通过运行时反射API获得annotation信息
            Class<?> rtClass = Class.forName("com.ddf.annotation.Utility");
            Method[] methods = rtClass.getMethods();

            boolean descriptionExist = rtClass.isAnnotationPresent(Description.class);
            if (descriptionExist) {
                Description description = rtClass.getAnnotation(Description.class);
                System.out.println("Utility's Description --- > " + description.value());

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Author.class)) {
                        Author author = method.getAnnotation(Author.class);
                        System.out.println("Utility's Author ---> " + author.name() + " from " + author.group());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
