package cn.edu.ecut.servlet.config;

import javax.servlet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

public class ConfigurationServlet implements Servlet {

    private ServletConfig config ;

    @Override
    public void init( ServletConfig config ) throws ServletException {
        System.out.println( "【 init 】 ==> " + config );
        this.config = config ;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config ;
    }

    @Override
    public void service(ServletRequest req , ServletResponse resp )
            throws ServletException, IOException {
        System.out.println( "【 service 】" );

        resp.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = resp.getWriter() ;

        // 获取所有初始化参数的名称
        Enumeration<String> enumeration = config.getInitParameterNames();
        /*
        while( enumeration.hasMoreElements() ) {
            String name =  enumeration.nextElement();
            // 注意 name 是个变量，其中存储的是个不同的 参数名称
            String value = config.getInitParameter( name );
            System.out.println( name + " : " + value );
            w.println( "<p align='center'>" +  name + " : " + value  + "</p>");
        }
        */
        // 将 enumeration 转换成 iterator
        Iterator<String> itor = enumeration.asIterator(); // JDK 9 开始为 Enumeration 接口 提供了 asIterator 方法
        while( itor.hasNext() ) {
            String name =  itor.next();
            // 注意 name 是个变量，其中存储的是个不同的 参数名称
            String value = config.getInitParameter( name );
            System.out.println( name + " : " + value );
            w.println( "<p align='center'>" +  name + " : " + value  + "</p>");
        }

    }

    @Override
    public String getServletInfo() {
        String s = "【 " + config.getServletName() + " - " + this + " 】" ;
        return s ;
    }

    @Override
    public void destroy() {
        System.out.println( "【 destroy 】" );
    }

}
