package cn.edu.ecut.servlet.dispatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/include/servlet" )
public class IncludeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();

        w.println( "<style type='text/css'>" );
        w.println( ".wrapper {" );
        w.println( "      border : 1px solid red ; " );
        w.println( "      margin : 30px auto ; " );
        w.println( "}" );
        w.println( "</style>" );

        String path ;
        RequestDispatcher dispatcher ;

        w.println( "<div class='wrapper'>" );
        path = "/WEB-INF/inner.html" ;
        dispatcher = request.getRequestDispatcher( path );
        dispatcher.include( request , response );
        w.println( "</div>" );

        w.println( "<div class='wrapper'>" );
        path = "/WEB-INF/inner.txt" ;
        dispatcher = request.getRequestDispatcher( path );
        dispatcher.include( request , response );
        w.println( "</div>" );

        w.println( "<div class='wrapper'>" );
        path = "/inner/servlet" ;
        dispatcher = request.getRequestDispatcher( path );
        dispatcher.include( request , response );
        w.println( "</div>" );

        /*
        w.println( "<div class='wrapper'>" );
        path = "/show/image" ;
        dispatcher = request.getRequestDispatcher( path );
        dispatcher.include( request , response ); // java.lang.IllegalStateException
        w.println( "</div>" );
        */

    }

}
