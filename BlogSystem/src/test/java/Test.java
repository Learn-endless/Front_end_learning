class A {
    public A(){
        System.out.println("A 的构造方法");
    }
    {
        System.out.println("A 的实例代码块");
    }
    static{
        System.out.println("A 的静态代码块");
    }
}

class B extends A{
    public B(){
        System.out.println("B 的构造方法");
    }

    {
        System.out.println("B 的实例代码块");
    }

    static{
        System.out.println("B 的静态代码块");
    }
}
public class Test extends B{
    public static void main(String[] args) {
        new Test();
        new Test();

//        结果:
//        A 的静态代码块
//        B 的静态代码块
//        A 的实例代码块
//        A 的构造方法
//        B 的实例代码块
//        B 的构造方法
//        A 的实例代码块
//        A 的构造方法
//        B 的实例代码块
//        B 的构造方法

//        总结:
//        1. 调用 main 方法之前先要进行 类加载,将所有用到的类加载到内存中，构造 类对象。
//        2. 类加载的过程中,会处理静态成员,并且只会执行一次。
//        3. 想要调用类的普通方法或成员变量，需要先创建实例。
//        4. 子类创建实例时，需要先帮父类创建实例。
//        5. 每一次new实例的时候都会调用构造方法。
    }
}
