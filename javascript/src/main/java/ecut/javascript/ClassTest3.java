package ecut.javascript;


public class ClassTest3 {

    public static void main(String[] args) {

        // 因为 Object 是一个类，所以在JVM中存在一个 Class 实例 来表示该类
        Class<?> c = Object.class; // Object 是整个 Java 类继承体系中的最顶层父类，它对应者一个 Class 实例
        System.out.println( c.getCanonicalName() );
        System.out.println( c.getSuperclass() );

        // 因为 Class 是一个类，所以在JVM中存在一个 Class 实例 来表示该类
        Class<?> cc = Class.class ; // Class 是表示 类型 的 类，因此它也应该有一个 Class 类型的实例
        System.out.println( cc.getCanonicalName() );
        Class<?> pc = cc.getSuperclass() ; // 获得 cc 变量指向的 Class 实例 所表示的类的父类
        System.out.println( pc.getCanonicalName() );

    }

}
