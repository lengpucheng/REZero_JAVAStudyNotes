package cn.hll520.java.annotionsample;

/**
 * @author lpc
 * @version 1.0  2020-11-24-16:15
 * @since 2020-11-24-16:15
 * 描述：
 */
public class Person {

    @MyAnnotation("Annotation SINGLE")
    public void single(){
        System.out.println("this is single");
    }

    @Deprecated
    @MyAnnotation({"LingLing","Hu"})
    public void couple(String name,String family){
        System.out.println("Family Name :"+family+"-"+name);
    }
}
