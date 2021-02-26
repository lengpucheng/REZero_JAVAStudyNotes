# Lambda 表达式

**lambda 可以把函数/方法作为一个对象或者参数**，其非常类似于之前的匿名接口或者匿名内部类，有如下语法：

+ 不需要声明参数类型, 如果声明则需要都声明
+ 参数用圆括号包裹，只有一个时可以省略括号
+ 方法主体用大括号包裹，只有一句时可以省略
+ 如果省略大括号且有返回值时，return可以省略
+ 参数和方法体之间用 **->** 连接

在表达式的方法体中只能使用**final局部变量，可以不声明，方法体中会默认为final**，在其中无法直接更改引用，但是**可以通过方法修改堆中的值**，对应全局变量没有限制



## 语法

```java
// 1. 不需要参数,返回值为 5  
() -> 5  
  
// 2. 接收一个参数(数字类型),返回其2倍的值  
x -> 2 * x  
  
// 3. 接受2个参数(数字),并返回他们的差值  
(x, y) -> x – y  
  
// 4. 接收2个int型整数,返回他们的和  
(int x, int y) -> x + y  
  
// 5. 接受一个 string 对象,并在控制台打印(void)  
(String s) -> System.out.print(s)
```



## 案例

```java
public class LambdaDemo {

    static MathOdd oddAll = new MathOdd(20);

    public static void main(String[] args) {
        // lambda 引用
        MathOperation add = Integer::sum;
        // lambda 表达式 全省略
        MathOperation division = (a, b) -> a / b;
        // lambda 表达式 带参数
        MathOperation multiplication = (int a, int b) -> a * b;
        // lambda 表达式 不省略括号
        MathOperation subtraction = (int a, int b) -> {
            return a - b;
        };
        System.out.println(operation(4, 2, add));
        System.out.println(operation(4, 2, division));
        System.out.println(operation(4, 2, multiplication));
        System.out.println(operation(4, 2, subtraction));
        // 直接修改全局变量
        MathOperation updateAll = (a, b) -> {
            // 全局变量可以修改
            oddAll = new MathOdd(40);
            oddAll.x = 20;
            return a + oddAll.x;
        };
        System.out.println(operation(4, 2, updateAll));
        System.out.println(oddAll);
        MathOdd mathOdd = new MathOdd(10);
        // 局部变量修改堆
        MathOperation updateNow = (a, b) -> {
            /*
             *  直接修改引用会报错 因为是 final 类型
             *   mathOdd=new MathOdd(10);
             *   mathOdd.x=40;
             * */
            // 局部变量可以通过方法引用修改
            mathOdd.setX(0);
            return a + oddAll.x;
        };
        System.out.println(operation(4, 2, updateNow));
        System.out.println(mathOdd);

    }

    private static int operation(int a, int b, MathOperation operation) {
        return operation.run(a, b);
    }

    interface MathOperation {
        int run(int a, int b);
    }

    static class MathOdd {
        private int x;

        public MathOdd(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        @Override
        public String toString() {
            return "MathOdd{" +
                    "x=" + x +
                    '}';
        }
    }
}
```



# Lambda :: 引用

双冒号（::）运算符在Java 8中被用作**方法引用（method reference）**，即使用当前的某个方法**作为Lambda的方法体**，非常类似于将方法进行封装后复用，其语法为**Class/instance::method**，有四种引用类型：

| 语法种类                         | 示例                                 |
| -------------------------------- | ------------------------------------ |
| 引用静态方法                     | ContainingClass::staticMethodName    |
| 引用特定对象的实例方法           | containingObject::instanceMethodName |
| 引用特定类型的任意对象的实例方法 | ContainingType::methodName           |
| 引用构造函数                     | ClassName::new                       |



## 语法

````java
// 1. 引用静态方法
LambdaColonDemo::printf
  
// 2. 引用特定对象的实例方法
System.out::println 
  
// 3. 引用构造器  
builds.forEach(Build::showName);
  
// 4. 引用构造数组 
Build::new 
````

其他规则如下：

+ 引用时候不需要声明参数
+ **surp::method**，可以引用超类的方法
+ **this::method**，引用当前的方法
+ **Class::new**，引用某一类的构造方法
+ **Class[]::new**，引用某一类的数组构造方法
+ 可以使用JDK自带的接口 **Function** 来接收



## 案例

```java
public class LambdaColonDemo {

    public static void main(String[] args) {
        // 静态引用
        List<Build> list= Arrays.asList(new Build("11", 11), new Build("22", 22), 
                new Build("33", 33));
        Operation show = LambdaColonDemo::printf;
        for (Build e : list)
            show.run(e);
        // 等同于
        list.forEach(LambdaColonDemo::printf);

        // 实例方法引用
        Build instance=new Build("22",22);
        Operation showBuild=instance::setName;
        System.out.println(instance);
        showBuild.run("33");
        System.out.println(instance);

        // 类型方法引用
        List<Build> builds= Arrays.asList(new Build("11", 11), new Build("22", 22),
                new Build("33", 33));
        builds.forEach(Build::showName);

        // 构造
        ToBuild build = Build::new;
        Build buildInstance = build.run("2", 2);
        System.out.println(buildInstance);

        // 构造数组 JDK自带的接口 Function
        Function<Integer, Build[]> function = Build[]::new;
        Build[] apply = function.apply(10);
        System.out.println(Arrays.toString(apply));
    }

    private static void printf(Object o) {
        System.out.println(o);
    }

    interface Operation {
        void run(Object o);
    }

    interface ToBuild {
        Build run(String a, int b);
    }

    static class Build {
        private String name;
        private int age;

        public Build() {
        }

        public String getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = (String) name;
        }

        public void showName(){
            System.out.println(name);
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Build(String name, int age) {
            this();
            this.name = name;
            this.age = age;
        }

        public static void show(Build build){
            System.out.println(build);
        }

        @Override
        public String toString() {
            return "Build{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
```

