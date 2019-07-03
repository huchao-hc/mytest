package cn.edu.ecut.servlet.request;

import cn.edu.ecut.helper.JdbcHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
  create table t_users (
        id int(10) auto_increment ,
        nickname varchar(30) ,
        username varchar(20) not null unique ,
        password varchar(32) not null ,
        gender varchar(6) ,
        birthdate date ,
        sign_up_address varchar(39) ,
        sign_up_time timestamp default current_timestamp ,
        constraint pk_users_id primary key ( id )
  );
 */

@WebServlet( "/user/sign/up"  )
public class UserSignUpServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        final String address = request.getRemoteAddr();

        response.setCharacterEncoding( "UTF-8" );

        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter w = response.getWriter();

        String nickname = request.getParameter( "nickname" );
        String username = request.getParameter( "username" );
        String password = request.getParameter( "password" );
        String confirm = request.getParameter( "confirm" );
        String gender = request.getParameter( "gender" );

        // 检查昵称是否为空
        if( nickname == null || ( nickname = nickname.trim() ).isEmpty() ) {
            w.println( "<p style='text-align : center ;'>" );
            w.println( "昵称不能为空，请返回重新填写" );
            w.println( "<a href='/request/sign-up.html'>返回</a>"  );
            w.println( "</p>" );
            return ; // 让 service 方法立即结束
        }

        // 应该检查 用户名、密码、确认密码、性别 是否为空

       // 检查两次输入密码是否一致
        if( password.equals( confirm) ) {
            // 注册
            String insert = "INSERT INTO t_users " +
                                   "( nickname , username , password , gender , sign_up_address )" +
                                    " VALUES ( ? , ? , ? , ? , ? )";

            JdbcHelper helper = JdbcHelper.getInstance();
            helper.update( insert , nickname , username , password , gender , address );
            helper.release();

            w.println( "<p style='text-align : center ;'>" );
            w.println( "注册成功" );
            w.println( "</p>" );

        } else {
            // 密码不一致
            w.println( "<p style='text-align : center ;'>" );
            w.println( "两次输入密码不一致，请返回重试" );
            w.println( "<a href='/request/sign-up.html'>返回</a>"  );
            w.println( "</p>" );
        }

    }

}
