package ecut.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet( "/user/check/username" )
public class UserCheckUsernameServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {

        response.setContentType( "text/plain;charset=UTF-8" );
        PrintWriter w = response.getWriter();

        String username = request.getParameter( "username" );

        Map<String,Object> map = new HashMap<>();

        if( username == null || ( username = username.trim() ).isEmpty() ){
            // json = "{ \"flag\" : false , \"message\": \"用户名不能为空\"}";
            map.put( "flag" , false );
            map.put( "message" ,  "用户名不能为空" );
        } else if( "zhangsanfeng".equals( username ) ){
            // json = "{ \"flag\" : false , \"message\": \"用户名已经被占用，请更换后重试\"}";
            map.put( "flag" , false );
            map.put( "message" ,  "用户名已经被占用，请更换后重试" );
        } else {
            // json = "{ \"flag\" : true ,\"message\": \"恭喜你，用户名可以使用\"}";
            map.put( "flag" , true );
            map.put( "message" ,  "恭喜你，用户名可以使用" );
        }

        String json = JSON.toJSONString( map ) ;
        System.out.println( "JSON : " + json );
        w.println( json );
    }
}
