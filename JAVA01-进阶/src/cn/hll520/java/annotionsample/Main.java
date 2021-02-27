package cn.hll520.java.annotionsample;

import java.util.Arrays;

/**
 * @author lpc
 * @version 1.0  2020-11-25-10:36
 * @since 2020-11-25-10:36
 * 描述：Annotation 全面总结
 */
@MyAnnotational(classPath = MyAnnotation.class)
public class Main {
    public static void main(String[] args) {
        Class<Person> personClass = Person.class;
        checkAnnotation(personClass);
        Class<Student> studentClass = Student.class;
        checkAnnotation(studentClass);
        Class<PersonSon> personSonClass = PersonSon.class;
        checkAnnotation(personSonClass);
        Class<StudentGirlFriend> studentGirlFriendClass = StudentGirlFriend.class;
        checkAnnotation(studentGirlFriendClass);
    }

    private static void checkAnnotation(Class<?> pramClass) {
        System.out.println("\n________" + pramClass + "_________");
        if (pramClass.isAnnotationPresent(MyComprehension.class)) {
            MyComprehension annotation = pramClass.getAnnotation(MyComprehension.class);
            System.out.println("Color is :" + annotation.color());
            System.out.println("Value is :" + Arrays.toString(annotation.value()));
            System.out.println("Annotation is : " + annotation.annotation().toString());
            System.out.println("Annotation value is : " + Arrays.toString(annotation.annotation().value()));
        }
        boolean annotationPresent = pramClass.isAnnotationPresent(MyAnnotation.class);
        System.out.println("whether have MyAnnotation :" + annotationPresent);
    }
}
@MyAnnotation("have Annotation inherited")
@MyComprehension("only value")
class Student {

}

class PersonSon extends Person {

}

class StudentGirlFriend extends Student {

}