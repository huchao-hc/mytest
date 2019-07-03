package cn.edu.ecut.servlet.core;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/general" )
public class GeneralServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response)
            throws ServletException, IOException {

        final String method = request.getMethod();
        System.out.println( "~ ~ ~ ~ ~ GeneralServlet # service : " + method + " ~ ~ ~ ~ ~" );

        String uname = request.getParameter( "username" );
        System.out.println( uname );
        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();
        w.println( "<h1>hello , " + method + " .</h1>" );

    }

}
