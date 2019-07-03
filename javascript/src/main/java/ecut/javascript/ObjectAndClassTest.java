package ecut.javascript;

/**
 *     类 ( Class )
 *    牛类( Bull ) : 牛魔王 、 红孩儿
 *    猴类( Monkey ) : 孙悟空 、六耳猕猴
 */
public class ObjectAndClassTest {

    public static void main(String[] args) {

        Object o = new Object();
        System.out.println( o ); // 实例( instance )

        Class<?> c = o.getClass() ; // 获取引用变量 o 指向的 Java 对象的类型
        // c 所引用的 Java 对象 表示 Object类 对应的 Class 对象
        System.out.println( c );

    }

}
