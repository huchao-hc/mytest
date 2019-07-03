package cn.edu.ecut.servlet.dispatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/zhao/gao" )
public class ZhaoGaoServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        System.out.println( "URI : " + request.getRequestURI() );
        System.out.println( "request : " + System.identityHashCode( request ) );
        System.out.println( "response : " + System.identityHashCode( response ) );

        // void   setAttribute( String attributeName , Object attributeValue );
        request.setAttribute( "territory" , "大秦帝国" );

        String path = "/ying/zheng" ;
        RequestDispatcher dispatcher = request.getRequestDispatcher( path );
        dispatcher.forward( request , response );
        return ;
    }

}
