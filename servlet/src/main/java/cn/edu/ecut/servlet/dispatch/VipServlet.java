package cn.edu.ecut.servlet.dispatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/vip/*" )
public class VipServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        System.out.println( "VipServlet # service" );

        String path = "/WEB-INF/vip.html" ;

        // 获得可以 转发 到指定 路径的 请求调度器 ( 请求派遣器 / 请求指派器 )
        RequestDispatcher dispatcher = request.getRequestDispatcher( path );
        // 使用 请求调度器 将 请求 和 响应 转发 ( forward ) 给 path 对应的资源
        dispatcher.forward( request , response );

    }

}
