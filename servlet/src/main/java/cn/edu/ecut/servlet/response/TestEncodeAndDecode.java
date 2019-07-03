package cn.edu.ecut.servlet.response;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class TestEncodeAndDecode {

    public static void main(String[] args) {

        final Charset utf8Charset = Charset.forName( "UTF-8" );
        final Charset gbkCharset = Charset.forName( "GBK" );

        final String source = "I am 张三丰" ;
        System.out.println( "Source => " + source );

        final String encoded =  URLEncoder.encode( source , utf8Charset );
        System.out.println( "Encoded : " + encoded );

        final String decoded = URLDecoder.decode( encoded , utf8Charset );
        System.out.println( "Decoded : " + decoded );

        String de = URLDecoder.decode( encoded , gbkCharset ) ;
        System.out.println( de );

    }

}
