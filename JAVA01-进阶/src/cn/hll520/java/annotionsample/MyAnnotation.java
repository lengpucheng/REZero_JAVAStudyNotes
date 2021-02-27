package cn.hll520.java.annotionsample;

import java.lang.annotation.*;

/**
 * @author lpc
 * @version 1.0  2020-11-24-15:37
 * @since 2020-11-24-15:37
 * 描述：
 */
@Documented
@Inherited
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String[] value() default "unknown";

}
