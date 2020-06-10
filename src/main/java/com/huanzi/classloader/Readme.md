###  还有待补充

###类加载的三个阶段
    加载:查找并且加载类的二进制数据
        将class文件二进制数据读取到内存,放到方法区(常量池)中,然后堆内存创建一个class对象,封装方法区的数据结构
        类加载的最终产品是位于堆区中的Class对象
        加载类的方式:
            本地磁盘中加载
            内存中直接加载
            网络加载
            zip,jar等文件中加载class文件
            数据库中提取class
            动态编译
    链接:
        验证:确保被加载类的正确性
        准备:为类的静态变量分配内存,并将其初始化为默认值
        解析:把类中的符号,引用转换为直接引用(更改指针指向)
    初始化:为类的静态变量赋予正确的初始值
        执行<init>方法,
        静态语法快中只能访问定义在静态语句块之前的变量,定义在之后的语句块只能赋值,不能访问
        <init>与类的构造函数有点区别,不需要显示的调用父类的构造函数,虚拟机会保证会<init>父类的<init>
        
        

### jvm规范: 对类的时候方式
- 主动使用 
- 被动使用
```$xslt
所有jvm必须在每个类或者接口被java程序首次使用的主动使用的时候才能初始化他们,
当然现代jvm有可能根据程序的上下文语义推断出接下来可能初始化谁!
```

访问某个类或者接口的静态变量,或者对该静态变量进行赋值操作
1.对某个类的静态变量进行读写 -->class
2.对接口中静态变量(默认final static)读取 -->interface

    