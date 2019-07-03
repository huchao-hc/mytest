package cn.edu.ecut.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet( "/parse/request"  )
public class ParseRequestServlet extends HttpServlet {

    @Override
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // 容器接受到 HTTP 请求后会首先解析 HTTP 请求，
        // 并将其中包含的数据封装到相应的 request 对象中
        // 随后容器调用 servlet 实例的 service 方法
        // 因此在 service 方法内部可以通过 request 对象来获取所有数据

        /* ~ ~ ~ Request Line ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ */
        final String method = request.getMethod() ;
        final String uri = request.getRequestURI();
        final String protocol = request.getProtocol();
        System.out.println( method + "\t" + uri + "\t" + protocol );

        /* ~ ~ ~ Request  Header ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ */
        // 获取本次请求中所有请求头字段的名称
        Enumeration<String> enumeration = request.getHeaderNames();
        Iterator<String> itor = enumeration.asIterator();
        while( itor.hasNext() ){
            String headerName = itor.next(); // 获得单个的 请求头 字段名称
            // 根据请求头字段名称获取其取值
            String headerValue = request.getHeader( headerName );
            System.out.println( headerName + " : " + headerValue );
        }

        System.out.println(); // 充当一个空行

        /* ~ ~ ~ Request Body ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ */
        // 这里仅仅获取 POST 表单所提交的文本数据
        BufferedReader br = request.getReader();
        String s = null ;
        // 必须在客户端通过 POST 方式提交表单才能在这里看到数据 ( 其它情况暂不考虑 )
        while( ( s = br.readLine() ) != null ){
            System.out.println( s );
        }

        // 如果 URL 中包含 Query String ，则可以通过 getQueryString() 方法来获取
        // 通过 GET 方式提交数据时，数据被附加在URL末尾( 以 Query String 形式 )
        // 【问题】当使用 POST 方式提交表单时，是否允许在 URL末尾使用 Query String ?
        String queryString = request.getQueryString();
        System.out.println( "Query String ==> " + queryString );

    }

}
