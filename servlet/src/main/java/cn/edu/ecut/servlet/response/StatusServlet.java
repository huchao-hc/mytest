package cn.edu.ecut.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( urlPatterns = { "/status" , "/status/servlet" } )
public class StatusServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String method = request.getMethod();
        System.out.println( "method : " + method );

        //response.setStatus( 404 );
        //response.sendError( 404 );
        response.sendError( 404 , "本来是可以访问的，但是就不让你访问" );

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter() ;
        w.println( "<p align='center'>再不好好学习你就是个屁</p>" );

    }

}
