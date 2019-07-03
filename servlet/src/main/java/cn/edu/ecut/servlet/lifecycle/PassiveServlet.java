package cn.edu.ecut.servlet.lifecycle;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PassiveServlet implements Servlet {

    static { // 静态代码块在类加载阶段的初始化阶段执行
        System.out.println( "类加载阶段: 对 PassiveServlet 类进行初始化" );
    }

    { // 创建实例时执行( 比构造方法还要先执行 )
        System.out.println( "创建 PassiveServlet 类的实例" );
    }

    public PassiveServlet(){
        System.out.println( "PassiveServlet 构造方法执行" );
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println( "容器调用 PassiveServlet 实例的 init 方法" );
    }


    @Override
    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        System.out.println( "容器调用 PassiveServlet 实例的 service 方法" );

        resp.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = resp.getWriter();
        w.println( this );
    }


    @Override
    public void destroy() {
        System.out.println( "容器调用 PassiveServlet 实例的 destroy 方法" );
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

}
