package cn.edu.ecut.servlet.dispatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/ying/zheng" )
public class YingZhengServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        System.out.println( "URI : " + request.getRequestURI() );

        // Object getAttribute( String attributeName )
        Object value = request.getAttribute( "territory" );
        System.out.println( "territory = " + value  );

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();
        w.println( "<h1 align='center'>朕乃大秦第一任皇帝</h1>" );

        System.out.println( "request : " + System.identityHashCode( request ) );
        System.out.println( "response : " + System.identityHashCode( response ) );

        // void   removeAttribute( String attributeName )
        request.removeAttribute( "territory" );
        value = request.getAttribute( "territory" );
        System.out.println( "territory = " + value  );

    }

}
