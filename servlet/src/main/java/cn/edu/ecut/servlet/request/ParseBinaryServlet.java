package cn.edu.ecut.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet( "/parse/binary" )
@MultipartConfig
public class ParseBinaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        throw new RuntimeException( "不支持 GET 方式上传文件" );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Servlet 3 开始提供了 javax.servlet.http.Part 接口来表示 multipart/form-data 表单上的每一部分数据
        Part first = request.getPart( "suibian" ); // <input type="text" name="suibian" placeholder="随便">
        System.out.println( first );

        // 根据表单中 指定控件名称 来获取 该控件 对应的数据
        Part second = request.getPart( "upfile" ) ; // <input type="file" name="upfile">
        System.out.println( second );

    }

}
