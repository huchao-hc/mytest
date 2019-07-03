package ecut.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet( "/user/sign/up" )
public class UserSignUpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();

        String username = request.getParameter( "username" );
        if( username == null || ( username = username.trim() ).isEmpty() ){
            w.println( "<h3 align='center'>用户名不能为空</h3>" );
            w.println( "请点击<a href='/ajax/sign-up.html'>这里</a>返回重新输入" );
            return ;
        }

        if( "zhangsanfeng".equals( username ) ){
            w.println( "<h3 align='center'>用户名[ " + username + " ]已经被占用，请重新输入</h3>" );
            w.println( "请点击<a href='/ajax/sign-up.html'>这里</a>返回重新输入" );
            return ;
        }

        w.println( "<h3 align='center'>注册成功</h3>" );
        return ;

    }
}
