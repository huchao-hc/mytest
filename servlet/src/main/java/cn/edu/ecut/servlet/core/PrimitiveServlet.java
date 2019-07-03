package cn.edu.ecut.servlet.core;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {

        System.out.println( "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ service ~ ~ ~ ~ ~ ~ ~ ~ ~ ~" ); // 向控制台输出字符串
        String name = Thread.currentThread().getName() ; // 获得当前线程的名称
        System.out.println( "Thread【" + name + "】" ); // 向控制台输出字符串
        System.out.println( "this ==> " + this ); // 向控制台输出字符串

        servletResponse.setContentType( "text/html;charset=UTF-8" );
        // servletResponse.setHeader( "content-type" , "text/html;charset=UTF-8" ) ;
        PrintWriter printWriter = servletResponse.getWriter();

        LocalDate date = LocalDate.now();

        // 通过可以向 浏览器 输出 字符数据的输出流 向 浏览器 发送数据
        printWriter.println( "<h1 style='text-align:center ; color:red ;'>" );
        printWriter.println( date );
        printWriter.println( "</h1>" );

    }

    @Override
    public void destroy() {
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

}
