package cn.edu.ecut.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet( "/show/image" )
public class ShowImageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        final String location = "D:/images" ;
        final String name = "yangmou.jpg" ;

        Path path = Paths.get( location , name ) ;

        // 对于 jpg 或  jpeg 图片来说将响应头中的 content-type 字段设置为 "image/jpeg"
        response.setHeader( "content-type" , "image/jpeg" ); // response.setContentType( "image/jpeg" );
        // 将响应头中的 content-disposition 字段设置为 "inline" 【默认值就是 inline 】
        response.setHeader( "content-disposition" , "inline" );

        // 获得可以向 response 输出字节数据的输出流
        OutputStream output = response.getOutputStream();

        // 将 path 对应的文件中的内容 复制到 output 流中 ( 最终通过  response 输出到了 浏览器 )
        Files.copy( path , output );

        /*
        File file = new File( location , name );
        InputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte[] bytes = new byte[ 1024 ];
        int n ;
        while( ( n = in.read( bytes ) ) != -1  ){
            out.write( bytes , 0 , n );
        }
        in.close();
        */

    }

}
