package cn.hll520.java.annotionsample;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author lpc
 * @version 1.0  2020-11-27-15:26
 * @since 2020-11-27-15:26
 * 描述：
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotational {
    Class<?> classPath() default MyAnnotational.class;


}
