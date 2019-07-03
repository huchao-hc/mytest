package cn.edu.ecut.servlet.lifecycle;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PositiveServlet implements Servlet {

    static {
        System.out.println( "【 static code block 】 : 类加载(初始化阶段) ==> " + PositiveServlet.class.getName()  );
    }

    {
        System.out.println( "【 normal code block 】 : 创建实例 ==> " + this );
    }

    public PositiveServlet(){
        System.out.println( "【 constructor 】: public PositiveServlet() " );
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println( "【 init 】: " + servletConfig.getServletName()  );
        System.out.println( "【 init 】: " + this  );
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        System.out.println( "【 service 】: " + this );

        resp.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = resp.getWriter();
        w.println( this );
    }

    @Override
    public void destroy() {
        System.out.println( "【 destroy 】: " + this );
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
