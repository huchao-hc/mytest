package ecut.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebServlet( "/get/student" )
public class GetStudentServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println( request.getRequestURI() );

        Map< String , String[] > paramMap = request.getParameterMap();
        Set< Map.Entry< String , String[] > > entries = paramMap.entrySet() ;
        for(Map.Entry< String , String[] > entry : entries){
            System.out.println( entry.getKey() + " : " + Arrays.toString( entry.getValue() ) );
        }

        response.setContentType( "text/plain;charset=UTF-8" );
        PrintWriter w = response.getWriter();

        String json = "{ \"id\" : 1001 , \"name\" : \"张翠山\" , \"gender\" : \"男\" }" ;

        w.println( json );

    }
}
