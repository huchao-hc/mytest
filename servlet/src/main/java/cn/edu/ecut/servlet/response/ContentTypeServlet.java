package cn.edu.ecut.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Collection;

@WebServlet( "/content/type" )
public class ContentTypeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String style = request.getParameter( "style" );

        style = style == null ? "" : style.trim().toLowerCase() ;

        // MIME , Multipurpose Internet Mail Extensions , 多用途互联网邮件扩展类型
        // "text/html" 表示 网页类型 ( HTML 文档 )
        // "text/css" 表示 CSS 样式类型
        // "text/javascript" 表示 JavaScript 类型
        // "image/jpeg" 表示 jpeg 图片格式
        // "text/plain" 表示纯文本类型
        String mime;

        // 使用 JDK 1.7 新增的 switch 支持 : 支持使用 String 类型
        switch ( style ) {
            case "html" :
                mime = "text/html;charset=UTF-8" ;
                break;
            case "text":
                mime =  "text/plain;charset=UTF-8" ;
                break;
            default:
                mime =  "application/octet-stream" ; // 任意文件对应的二进制流
        }

        /*  ~ ~ ~ ~ ~ ~ 设置响应头中 content-type 字段的值 ~ ~ ~ ~ ~ ~ */
         response.setHeader( "content-type" , mime ); // response.setContentType( mime );

        if( "application/octet-stream".equals( mime ) ) {
            // 获得可以向 response 输出数据的输出流，通过该流输出的所有字节最终都会存在于HTTP 响应体中
            OutputStream out = response.getOutputStream();
            PrintStream ps = new PrintStream( out );

            ps.println("<div style='text-align : center ; padding : 15px ; border : 1px solid blue ;'>");
            ps.println(LocalDateTime.now());
            ps.println("</div>");

        } else {
            // 获得可以向 response 输出数据的输出流，通过该流输出的所有字符最终都会存在于HTTP 响应体中
            PrintWriter w = response.getWriter();
            w.println("<div style='text-align : center ; padding : 15px ; border : 1px solid blue ;'>");
            w.println(LocalDateTime.now());
            w.println("</div>");
        }

        System.out.println( "~ ~ ~ ~ ~ ~ ~ ~~ ~ ~ ~ ~ ~ ~ ~~ ~ ~ ~ ~ ~ ~ ~" );

        Collection<String> headerNames = response.getHeaderNames();
        for( String name : headerNames ){
            Collection<String> values = response.getHeaders( name );
            System.out.println( name + " : " + values );
        }

    }

}
