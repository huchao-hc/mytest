package cn.edu.ecut.servlet.core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


// 1、继承 HttpServlet  类
// 3、为 HelloServlet 添加注解，并指定前端访问路径
@WebServlet( "/hello" )
public class HelloServlet extends HttpServlet {

    // 2、重写 service 方法
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding( "UTF-8" );
        response.setCharacterEncoding( "UTF-8" );

        LocalDateTime now = LocalDateTime.now();

        response.setContentType( "text/html;charset=UTF-8" );
        // 通过 response 获得字符输出流
        PrintWriter w = response.getWriter();

        w.println( "<html>" );
        w.println( "<head>" );
        w.println( "<meta charset='UTF-8'>" );
        w.println( "<title>现在时间是</title>" );
        w.println( "</head>" );
        w.println( "<body>" );
        w.println( "<h1 align='center'>");

        w.println( now ); // now.toString()

        w.println( "</h1>");
        w.println( "</body>" );
        w.println( "</html>" );

    }

}
