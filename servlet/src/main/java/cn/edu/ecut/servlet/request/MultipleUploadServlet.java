package cn.edu.ecut.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

public class MultipleUploadServlet extends HttpServlet {

    private String storePath ;

    @Override
    public void init() throws ServletException {
        storePath = this.getInitParameter( "path" );
        if( storePath == null || ( storePath = storePath.trim() ) .isEmpty() ) {
            throw new RuntimeException( "必须指定上传文件的保存路径" );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        throw new RuntimeException( "不支持 GET 方式上传文件" );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       Collection<Part> parts = request.getParts();

       if( parts !=null && parts.size() > 0 ) {
           for( Part p : parts ){
               // 使用 Servlet 3.1 新增的方法获取 原始文件名称 ( 客户端通过浏览器选择的文件的名称 )
               String filename = p.getSubmittedFileName();
               if( filename == null || ( filename = filename.trim() ).isEmpty() ) {
                   System.out.println( "请先选择文件后再提交" );
               } else {
                   InputStream in = p.getInputStream();
                   Path target = Paths.get( storePath , filename );
                   Files.copy( in , target , StandardCopyOption.REPLACE_EXISTING );
                   System.out.println( "文件[ " + filename + " ]上传成功" );
               }
           }
       }

    }

}
