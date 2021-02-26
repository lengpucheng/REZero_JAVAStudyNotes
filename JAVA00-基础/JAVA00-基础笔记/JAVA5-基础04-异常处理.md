# 异常处理

广义的异常分为`Error`和`Exception`

+ `Error`是JVM无法处理，主要是`StackOverflowError`栈溢出和`OutOfMemoryError`堆溢出

+ `Exception`是狭义上的异常，是JVM可以处理的，可以使用`try-cahche`捕获，进行处理，分为**运行时异常**和**编译时异常**
  + 编译时异常，在javac编译时检出
  + 运行时异常，在java运行时才可能发生

**JAVA异常抓抛模型**：

1. 在执行过程中一旦出现异常将**生成一个对应异常类对象，并将其抛出，且程序终止执行**。

2. 当异常对象生成后，可以使用`try-catch-finally`抓住异常进行处理，之后程序继续。也可以继续向外抛出等待后续处理。

**Exception有两个常用方法**：

+ getMessage(): 接收异常信息
+ printStackTrace(): 打印异常信息

## 1. 异常处理

### 1.1 捕获方式

```java
 		try{
			// 可能有异常的代码
        }catch (异常1类 e){
            // 异常1 的处理方式
        }catch (异常2类 e){
            // 异常2 的处理方式
        }finally {// 可选
            // 无论是否异常一定执行的代码
        }
```

以上是处理异常的通用写法，其中`finally`可选，**`try`中一旦出现异常，之后的代码不再执行**，和之后的`catch`进行匹配，若捕获处理后进入`finally`。多个`catch`捕获时，**若有子父类关系，父类必须在下**。

**`finally`是一定执行的代码**，即使在`catch`出现异常或者其他地方结束，**在结束/返回前依然会执行**，**主要用来关闭资源**



### 1.2 抛出方式

使用`throws`将异常向外抛出，让调用类来处理异常。

```java
 public void show() throws 异常类{
       // 可能有异常的代码
    }
```

可以抛出多个异常，**抛出的异常必须是大于可能出现的异常**（父类），抛出异常之后的代码不会再执行，子类**重写方法抛出的异常不能大于父类**抛出的异常（子类）



## 2. 异常抛出

异常可以使用`throw`手动抛出指定或自定义异常

```java
public void show(int num){
        if(num<0)
            throw new RuntimeException("num!<0");
    }
```

### 2.1 自定义异常

自定义异常类，继承于已有的异常结构（通常`RuntimeException`或`Exception`），定义序列号，重载构造器。

```java
class NewException extends RuntimeException {
    static final long serialVersionUID = -939L;

    public NewException(String msg) {
        super(msg);
    }
}
```



