package cn.edu.ecut.servlet.core;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class PrimaryServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {

        System.out.println( "PrimaryServlet : service" );

        // 获得可以向 Web 客户端 ( 浏览器 ) 发送 字符数据的 输出流
        PrintWriter w = servletResponse.getWriter();

        LocalDateTime datetime = LocalDateTime.now();
        //LocalDateTime datetime =  LocalDateTime.of( 1998 , 10 , 20 , 7 , 30 , 50 , 100200300 );

        w.println( "<div style='text-align:center;font-size:100px;color:red;'>");
        w.println( datetime );
        w.println( "</div>");

    }

}
