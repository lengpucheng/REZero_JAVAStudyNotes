package cn.hll520.java.annotionsample;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lpc
 * @version 1.0  2020-11-25-10:26
 * @since 2020-11-25-10:26
 * 描述：
 */
//
//           TYPE,               /* 类、接口（包括注释类型）或枚举声明  */
//
//            FIELD,              /* 字段声明（包括枚举常量）  */
//
//            METHOD,             /* 方法声明  */
//
//            PARAMETER,          /* 参数声明  */
//
//            CONSTRUCTOR,        /* 构造方法声明  */
//
//            LOCAL_VARIABLE,     /* 局部变量声明  */
//
//            ANNOTATION_TYPE,    /* 注释类型声明  */
//
//            PACKAGE             /* 包声明  */

//                SOURCE,            /* Annotation信息仅存在于编译器处理期间，编译器处理完之后就没有该Annotation信息了  */
//
//                CLASS,             /* 编译器将Annotation存储于类对应的.class文件中。默认行为  */
//
//                RUNTIME            /* 编译器将Annotation存储于class文件中，并且可由JVM读入 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyComprehension {

    // 枚举类型的
    Color color() default Color.RED;

    // 默认类型,不加default是必填项目，可以省略value，若为一个可以省略{}
    String[] value();

    // 注解类型 默认用一个注解填充
    MyAnnotation annotation() default @MyAnnotation("Comprehensive");

}

