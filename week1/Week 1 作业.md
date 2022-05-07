# Week 1 作业

**1.（选做）**自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。

``` java
public class HelloByteCode {
    public static void main(String[] args) {
        HelloByteCode instance = new HelloByteCode();
        int[] numbers = new int[]{1,2,3} ;
        int[] result = instance.toEven(numbers);
    }

    // 0 2 4 -> 0, 2, 4
    // 1 3 5 -> 2, 4, 6
    public int[] toEven(int[] numbers) {
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            // if even
            if ((number & 1) == 0) {
                result[i] = number; 
            } else {
                result[i] = number + 1;
            }
        }
        return result;
    }
}
```

使用命令

```shell
javac -g HelloByteCode.java
```

可以得到这段代码对应的字节码，加了-g以后可以得到`LocalVariableTable`

```java
Classfile /c:/Users/zhaomu/Desktop/javaCamp/Code/HelloByteCode.class
  Last modified 2022��4��28��; size 509 bytes
  MD5 checksum 085febf3dbd86ac22eeb48ff6aae0a39
  Compiled from "HelloByteCode.java"
public class HelloByteCode
  minor version: 0
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #2                          // HelloByteCode
  super_class: #5                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 3, attributes: 1
Constant pool:
   #1 = Methodref          #5.#18         // java/lang/Object."<init>":()V
   #2 = Class              #19            // HelloByteCode
   #3 = Methodref          #2.#18         // HelloByteCode."<init>":()V
   #4 = Methodref          #2.#20         // HelloByteCode.toEven:([I)[I
   #5 = Class              #21            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               main
  #11 = Utf8               ([Ljava/lang/String;)V
  #12 = Utf8               toEven
  #13 = Utf8               ([I)[I
  #14 = Utf8               StackMapTable
  #15 = Class              #22            // "[I"
  #16 = Utf8               SourceFile
  #17 = Utf8               HelloByteCode.java
  #18 = NameAndType        #6:#7          // "<init>":()V
  #19 = Utf8               HelloByteCode
  #20 = NameAndType        #12:#13        // toEven:([I)[I
  #21 = Utf8               java/lang/Object
  #22 = Utf8               [I
{
  public HelloByteCode();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHelloByteCode;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=4, args_size=1
         0: new           #2                  // class HelloByteCode
         3: dup
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1
         8: iconst_3
         9: newarray       int
        11: dup
        12: iconst_0
        13: iconst_1
        14: iastore
        15: dup
        16: iconst_1
        17: iconst_2
        18: iastore
        19: dup
        20: iconst_2
        21: iconst_3
        22: iastore
        23: astore_2
        24: aload_1
        25: aload_2
        26: invokevirtual #4                  // Method toEven:([I)[I
        29: astore_3
        30: return
      LineNumberTable:
        line 5: 0
        line 6: 8
        line 7: 24
        line 9: 30

  public int[] toEven(int[]);
    descriptor: ([I)[I
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=4, locals=5, args_size=2
         0: aload_1
         1: arraylength
         2: newarray       int
         4: astore_2
         5: iconst_0
         6: istore_3
         7: iload_3
         8: aload_1
         9: arraylength
        10: if_icmpge     46
        13: aload_1
        14: iload_3
        15: iaload
        16: istore        4
        18: iload         4
        20: iconst_1
        21: iand
        22: ifne          33
        25: aload_2
        26: iload_3
        27: iload         4
        29: iastore
        30: goto          40
        33: aload_2
        34: iload_3
        35: iload         4
        37: iconst_1
        38: iadd
        39: iastore
        40: iinc          3, 1
        43: goto          7
        46: aload_2
        47: areturn
      LineNumberTable:
        line 14: 0
        line 15: 5
        line 16: 13
        line 18: 18
        line 19: 25
        line 21: 33
        line 15: 40
        line 24: 46
      StackMapTable: number_of_entries = 4
        frame_type = 253 /* append */
          offset_delta = 7
          locals = [ class "[I", int ]
        frame_type = 252 /* append */
          offset_delta = 25
          locals = [ int ]
        frame_type = 250 /* chop */
          offset_delta = 6
        frame_type = 250 /* chop */
          offset_delta = 5
}
SourceFile: "HelloByteCode.java"

```

接下来分段分析 

1.1 类文件信息

```java
Classfile /c:/Users/zhaomu/Desktop/javaCamp/Code/HelloByteCode.class
  Last modified 2022��4��28��; size 509 bytes
  MD5 checksum 085febf3dbd86ac22eeb48ff6aae0a39
  Compiled from "HelloByteCode.java"
```

- class文件的路径
- 修改时间和文件大小
- MD5校验
- 源文件信息

1.2 类的基本信息

```java
public class HelloByteCode
  minor version: 0
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #2                          // HelloByteCode
  super_class: #5                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 3, attributes: 1
```

- class的名称（携带package信息）

- 小版本号

- 大版本号 55-44 = 11 说明JDK的版本是11

- ACC_PUBLIC 说明是一个公开类

- 本Class所对应在常量池里的类引用为编号为#2的条目（Item）， 其父类的类引用编号则是 #5


1.3 常量池

```java
Constant pool:
   #1 = Methodref          #5.#18         // java/lang/Object."<init>":()V
   #2 = Class              #19            // HelloByteCode
   #3 = Methodref          #2.#18         // HelloByteCode."<init>":()V
   #4 = Methodref          #2.#20         // HelloByteCode.toEven:([I)[I
   #5 = Class              #21            // java/lang/Object
   #6 = Utf8               <init>
   #7 = Utf8               ()V
   #8 = Utf8               Code
   #9 = Utf8               LineNumberTable
  #10 = Utf8               main
  #11 = Utf8               ([Ljava/lang/String;)V
  #12 = Utf8               toEven
  #13 = Utf8               ([I)[I
  #14 = Utf8               StackMapTable
  #15 = Class              #22            // "[I"
  #16 = Utf8               SourceFile
  #17 = Utf8               HelloByteCode.java
  #18 = NameAndType        #6:#7          // "<init>":()V
  #19 = Utf8               HelloByteCode
  #20 = NameAndType        #12:#13        // toEven:([I)[I
  #21 = Utf8               java/lang/Object
  #22 = Utf8               [I
```
- #1 = Methodref #5.#18这是一个方法引用， 类引用是 #5， 方法名引用则是 #18
- #2 = Class #19 这是一个类引用 类名用到了 #19 的Utf8字符串常量
- #20 = NameAndType #12：#13 这是一个方法定义，包括方法名，参数和返回值，由#12 和 #13 字符串常量拼接而成，意思是方法名为toEven， 参数是一个int数组， 返回值也是一个int数组 
  

1.4 构造函数
```java
public HelloByteCode();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHelloByteCode;
```
- `descriptor: ()V`： 方法描述符

- `stack=1, locals=1, args_size=1`：栈的最大深度为1，局部变量的槽位数（Slot）为1，参数的个数为1。构造函数是一种特殊的实例方法, 在里面可以引用 `this`; 在执行实例方法时, 要先确定通过哪个对象来调用这个方法。因此， JVM在调用之前需要先把this压进操作数栈, 然后拷贝/重用到构造函数的局部变量槽位中。

1.5 相关补充 

**JVM array descriptors.**

```java
[Z = boolean
[B = byte
[S = short
[I = int
[J = long
[F = float
[D = double
[C = char
[L = any non-primitives(Object)
```

**2.（必做）**自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 Hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件在我的教室下载。



```java
package week01;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader{
    public static final String PATH = "C:\\Users\\zhaomu\\Desktop\\JavaCampCode\\resource\\week01\\Hello.xlass";

    public static void main(String[] args) {
        try {
            Class hello = new HelloClassLoader().findClass("Hello");
            hello.getMethod("hello").invoke(hello.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get(PATH);
        byte[] bytesOfFile = new byte[0];
        try {
            bytesOfFile = Files.readAllBytes(path);
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] decodedByteCode = decode(bytesOfFile);
        
        return defineClass(name, decodedByteCode, 0, decodedByteCode.length);
    }

    private byte[] decode(byte[] bytesOfFile) {
        int length = bytesOfFile.length;
        for (int i = 0; i < length; i++) {
            bytesOfFile[i] = (byte) (255 - bytesOfFile[i]);
        }
        return bytesOfFile;
    }
}
```



**3.（必做）**画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

- Xmx 指定最大堆内存。不包括栈内存和非堆内存
- Xms 指定堆内存空间的初始大小。并非操作系统实际的初始值
- Xmn 等价于-XX：NewSize
- Meta 元空间
- DirectMemory 堆外内存
- Xss 设置每个线程栈的字节数，影响栈的深度

![JMM Parameter.drawio](.\JMM Parameter.drawio.svg)
