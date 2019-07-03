package ecut.javascript;

import javax.servlet.DispatcherType;
import java.time.LocalDate;
import java.util.List;

/**
 *                                java.lang.Class
 *           (instance)            牛类( Bull )  : 牛魔王(instance)、牛圣婴(牛红孩儿) (instance)
 *           (instance)         人类( Human ) : 牛顿 (instance) 、牛根生 (instance)
 *                                           int         :   100 、-20 、 0
 */
public class ClassTest1 {

    public static void main(String[] args) {

        // 任意 类型 都可以通过 .class 来获取该类型对应的对象
        // 注意: 将类型本身当作对象对待，比如 int 类型
        System.out.println( byte.class );
        System.out.println( short.class );
        System.out.println( int.class );
        System.out.println( long.class );
        System.out.println( float.class );
        System.out.println( double.class );
        System.out.println( char.class );
        System.out.println( boolean.class );

        System.out.println( "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " );

        System.out.println( byte[].class );
        System.out.println( short[].class );
        System.out.println( int[].class );
        System.out.println( long[].class );
        System.out.println( float[].class );
        System.out.println( double[].class );
        System.out.println( char[].class );
        System.out.println( boolean[].class );

        System.out.println( "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " );

        System.out.println( byte[][].class );
        System.out.println( short[][].class );
        System.out.println( int[][].class );
        System.out.println( long[][].class );
        System.out.println( float[][].class );
        System.out.println( double[][].class );
        System.out.println( char[][].class );
        System.out.println( boolean[][].class );

        System.out.println( "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " );

        System.out.println( String.class );
        System.out.println( Integer.class );
        System.out.println( Object.class );
        System.out.println( LocalDate.class );
        System.out.println( List.class );
        System.out.println( Override.class );

    }

}
