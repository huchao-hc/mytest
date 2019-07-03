package cn.edu.ecut.servlet.dispatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/inner/servlet" )
public class InnerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();

        w.println( "<p style='margin : 15px ; border : 1px solid green ; padding 15px ; '>" );
        w.println( "inner servlet." );
        w.println( "</p>" );

    }

}
