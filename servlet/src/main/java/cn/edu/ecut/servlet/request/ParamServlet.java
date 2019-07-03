package cn.edu.ecut.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet( "/param" )
public class ParamServlet   extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w =  response.getWriter();

        // 获取所有的请求参数的名称
        Enumeration<String> enumeration = request.getParameterNames();
        Iterator<String> itor = enumeration.asIterator();

        while( itor.hasNext() ){
            String paramName = itor.next();

            // String paramValue  = request.getParameter( paramName ); // 根据指定的参数名称 获取 相应的取值 ( 单个取值 )

            String[] paramValues = request.getParameterValues( paramName ) ; // 根据指定的参数名称 获取 相应的取值 ( 所有值 )
            System.out.println( paramName + " : " + Arrays.toString( paramValues ) );
            w.println( "<p align='center'>" + paramName + " : " + Arrays.toString( paramValues ) + "</p>" );
        }

        System.out.println( "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~" );

        // 获取所有的 请求参数 组成的 Map 集合
        Map< String , String[] > map = request.getParameterMap();

        // 将 map 集合中的 key-value 输出
        Set< Map.Entry< String , String[] > > entries = map.entrySet() ; // 获得 map 集合中所有的 entry 组成的 Set 集合
        for( Map.Entry< String , String[] > entry : entries ){
            System.out.println( entry.getKey() + " : " + Arrays.toString(  entry.getValue() ) );
        }

    }

}
