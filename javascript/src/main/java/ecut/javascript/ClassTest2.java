package ecut.javascript;

import java.util.Date;

/**
 *                                java.lang.Class
 *           (instance)            牛类( Bull )  : 牛魔王(instance)、牛圣婴(牛红孩儿) (instance)
 *           (instance)         人类( Human ) : 牛顿 (instance) 、牛根生 (instance)
 *                                           int         :   100 、-20 、 0
 */
public class ClassTest2 {

    public static void main(String[] args) {

        Object o = null ; // 声明一个 Object 类型的引用变量
        Class<?> c = null ; // 声明一个 Class 类型的引用变量

        // 父类类型的引用变量 指向 子类类型的对象 ( 父类引用指向子类对象 )
        o = new Date();
        // 任意 "引用类型的变量" 都可以通过 getClass() 方法
        c = o.getClass();
        //  来获取 该变量 o 所引用的对象 的 类型对象( 它是 Class 类型的实例 )
        System.out.println( "Name : " + c.getName() );
        System.out.println( "Canonical Name : " + c.getCanonicalName() );
        System.out.println( "Simple Name : " + c.getSimpleName() );

        o = new Computer.CPU();
        c = o.getClass(); // 任意 "引用类型的变量" 都可以通过 getClass() 方法来获取 该变量 o 所引用的对象 的 类型对象
        System.out.println( "Name : " + c.getName() );
        System.out.println( "Canonical Name : " + c.getCanonicalName() );
        System.out.println( "Simple Name : " + c.getSimpleName() );


    }

}
