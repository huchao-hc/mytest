package cn.edu.ecut.servlet.core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/dispatch" )
public class DispatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println( "~ ~ ~ ~ ~ DispatchServlet : doGet ~ ~ ~ ~ ~" );
        String uname = request.getParameter( "username" );
        System.out.println( uname );
        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();
        w.println( "<h1>hello , get .</h1>" );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println( "~ ~ ~ ~ ~ DispatchServlet : doPost ~ ~ ~ ~ ~" );
        String uname = request.getParameter( "username" );
        System.out.println( uname );
        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();
        w.println( "<h1>hello , post .</h1>" );
    }

}
