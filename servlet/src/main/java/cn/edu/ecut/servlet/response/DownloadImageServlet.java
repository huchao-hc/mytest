package cn.edu.ecut.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet( "/download/image" )
public class DownloadImageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        final Charset utf8 = Charset.forName( "UTF-8" );

        final String location = "D:/images" ;
        final String name = "杨某.jpg" ;

        Path path = Paths.get( location , name ) ;

        response.setHeader( "content-type" , "application/octet-stream" );
        response.setHeader( "content-disposition" , "attachment;filename=" + URLEncoder.encode( name , utf8 ));

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
