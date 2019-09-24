package com.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ddf on 16/2/2.
 *
 * From: http://josh-persistence.iteye.com/blog/2226493
 *
 * 注解原理:
 *　　Annotation其实是一种接口。通过Java的反射机制相关的API来访问Annotation信息。相关类（框架或工具中的类即使用注解的类）根据这些信息来决定如何使用该程序元素或改变它们的行为。
 * 　 Annotation和程序代码的隔离性：Annotation是不会影响程序代码的执行，无论Annotation怎么变化，代码都始终如一地执行。
 *　　忽略性：Java语言解释器在工作时会忽略这些annotation，因此在JVM 中这些Annotation是“不起作用”的，只能通过配套的工具才能对这些Annontaion类型的信息进行访问和处理。

 *　　Annotation与interface的异同：
 *　　1）、Annotation类型使用关键字@interface而不是interface。
 *　　    这个关键字声明隐含了一个信息：它是继承了java.lang.annotation.Annotation接口，并非声明了一个interface。
 *　　2）、Annotation类型、方法定义是独特的、受限制的。
 *　　    Annotation 类型的方法必须声明为无参数、无异常抛出的。
 *       这些方法定义了Annotation的成员：方法名成为了成员名，而方法返回值成为了成员的类型。方法返回值类型必须为primitive类型、Class类型、
 *       枚举类型、annotation类型或者由前面类型之一作为元素的一维数组。方法的后面可以使用 default和一个默认数值来声明成员的默认值，null
 *       不能作为成员默认值，这与我们在非Annotation类型中定义方法有很大不同。
 *　　3）、Annotation类型又与接口有着近似之处。
 *　　    它们可以定义常量、静态成员类型（比如枚举类型定义）。Annotation类型也可以如接口一般被实现或者继承。

 *
 * 使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。
 *
 * @interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，返回值类型
 * 就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。可以通过default来声明参数的默认值。
 *
 * 定义注解格式：
 *       public @interface 注解名 {定义体}
 *
 * 注解参数的可支持数据类型：

 *　　　　1.所有基本数据类型（int,float,boolean,byte,double,char,long,short)
 *　　　　2.String类型
 *　　　　3.Class类型
 *　　　　4.enum类型
 *　　　　5.Annotation类型
 *　　　　6.以上所有类型的数组

 *　　Annotation类型里面的参数该怎么设定:
 *　　第一,只能用public或默认(default)这两个访问权修饰.例如,String value();这里把方法设为defaul默认类型；　 　
 *　　第二,参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和 String,Enum,Class,annotations等数据类型,以及这一些类型的数组.例如,String value();这里的参数成员就为String;　　
 *　　第三,如果只有一个参数成员,最好把参数名称设为"value",后加小括号.
 */


/**
 * 注解的属性也叫做成员变量。注解只有成员变量，没有方法。注解的成员变量在注解的定义中以“无形参的方法”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。
 * 赋值的方式是在注解的括号内以 value=”” 形式，多个属性之前用 ，隔开。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Description {

    String value();// 只有一个属性时，最好定义为value，因为：如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内。
}
