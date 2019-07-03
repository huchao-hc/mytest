package cn.edu.ecut.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(  "/single/upload"  )
@MultipartConfig( location = "D:/upload" )
public class SingleUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        throw new RuntimeException( "不支持 GET 方式上传文件" );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 根据 控件名称 获取 相应部分的数据对应的 Part 对象
        Part part = request.getPart( "upfile" ); // <input type="file" name="upfile" >
        if( part == null ) {
            System.out.println( "控件名称错误" );
        } else {
            // 使用 Servlet 3.1 新增的方法获取 原始文件名称 ( 客户端通过浏览器选择的文件的名称 )
            String filename = part.getSubmittedFileName();
            if( filename == null || ( filename = filename.trim() ).isEmpty() ) {
                System.out.println( "请先选择文件后再提交" );
            } else {
                // 将 part 对象中所包含的文件内容 写出到 location 目录中
                // 同时，使用 客户端上传的文件的原始名称 作为目标文件名称
                part.write( filename );
                System.out.println( "文件[ " + filename + " ]上传成功" );
            }
        }

    }

}
