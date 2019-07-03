package ecut.javascript;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class URLDecodeAndEncodeTest {

    public static void main(String[] args) {

        Charset charset = Charset.forName( "UTF-8" );

        String url = "http://www.baidu.com/s?w=张三丰" ;
        // String url = "张三丰" ;
        System.out.println( url );

        // 编码 ( encode )
        String encodedUrl  = URLEncoder.encode( url , charset );
        System.out.println( encodedUrl );

        // 解码 ( decode )
        String decodeUrl =  URLDecoder.decode( encodedUrl , charset );
        System.out.println( decodeUrl );

    }

}
