package cn.edu.ecut.servlet.config;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet( urlPatterns = { "/init" , "/init/param" } ,
                       initParams = {
                            @WebInitParam( name = "username" , value = "ecuter" ) ,
                            @WebInitParam( name = "password" , value = "ecutnb" )
                       } )
public class InitParamServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //  容器调用当前 servlet 实例的 init( ServletConfig ) 方法
        // 而 HttpServlet 类继承了 GenericServlet 类的 init( ServletConfig ) 方法
        // 在 GenericServlet 类中 init( ServletConfig ) 方法调用了另外一个 无参数的 init 方法 ( 也就是现在所重写的方法 )
        System.out.println( "【 InitParamServlet # init 】" );
    }

    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {
        System.out.println( "【 InitParamServlet # service 】" );

        // 容器调用当前 servlet 实例的 service( ServletRequest , ServletResponse ) 方法
        // 而 HttpServlet 类重写 GenericServlet 类中的了 service( ServletRequest , ServletResponse ) 方法，
        // 并在其中调用了 service( HttpServletRequest , HttpServletResponse )

        System.out.println( "this is Servlet : " + ( this instanceof Servlet ) );
        System.out.println( "this is ServletConfig : " + ( this instanceof  ServletConfig ) );

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter() ;

        // 获取所有初始化参数的名称
        Enumeration<String> enumeration = this.getInitParameterNames();

        Iterator<String> itor = enumeration.asIterator(); // JDK 9 开始为 Enumeration 接口 提供了 asIterator 方法
        while( itor.hasNext() ) {
            String name =  itor.next();
            // 注意 name 是个变量，其中存储的是个不同的 参数名称
            String value = this.getInitParameter( name );
            System.out.println( name + " : " + value );
            w.println( "<p align='center'>" +  name + " : " + value  + "</p>");
        }

    }

    @Override
    public void destroy() {
        System.out.println( "【 InitParamServlet # destory 】" );
    }

}
