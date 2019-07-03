package cn.edu.ecut.servlet.response;

import cn.edu.ecut.helper.GraphicHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet( "/captcha/*" )
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        response.setHeader( "content-type" , "image/jpeg"  );

        // 获得可以向 response 输出数据的字节输出流
        OutputStream output = response.getOutputStream();

        // 通过 create 方法动态创建验证码图片并将图片内容输出到 output 中
        String code = GraphicHelper.create( 4 ,true, 180 , 50 ,  output , 80 );
        System.out.println( "code = " + code  );

    }

}
