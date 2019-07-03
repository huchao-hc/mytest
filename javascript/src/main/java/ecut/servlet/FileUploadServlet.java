package ecut.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet( "/file/upload/*" )
@MultipartConfig( location = "D:/upload" )
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {
        throw new ServletException( "不支持以GET方式上传文件" );
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response )
            throws ServletException, IOException {

        Map<String,String> map = new HashMap<>();
        // 获取由客户端上传的文件
        Collection<Part> parts = request.getParts();
        if( parts != null && parts.size() > 0 ) {
            for( Part p : parts ){
                String filename = p.getSubmittedFileName() ; // 获得上传文件的原始名称
                // 文件名不为空且不为空串
                if( filename != null &&  !filename.trim().isEmpty() ) {
                    try {
                        //此时，可以将文件内容写出到【约定位置】
                        p.write(filename); // 将文件内容保存到【约定位置】并且名称为 filename
                        map.put( filename , "上传成功");
                    } catch ( Exception e ) {
                        System.out.println( "上传失败: " + e.getMessage() );
                        map.put( filename , "上传失败");
                    }
                }
            }
        }

        // 从 请求对象 中获取 本次请求路径
        String uri = request.getRequestURI();
        System.out.println( uri );

        String type = null ;
        int last = uri.lastIndexOf( "/" ); // 从 uri 中获取最后一个 / 的索引
        if( last != uri.length() - 1 ) { // 如果 最后一个 / 不是 整个 字符串 最后一个字符
            type = uri.substring( last + 1 );
            System.out.println( type );
        }


        if( "synchronize".equals( type ) ) {
            response.setContentType( "text/html;charset=UTF-8" );
            PrintWriter w = response.getWriter();

            // "迭代Map集合"
            Set< Map.Entry<String,String> > entries = map.entrySet();
            for ( Map.Entry<String,String> entry : entries ){
                w.println( "<div>[ " + entry.getKey() + " ]" + entry.getValue()+" </div>" );
            }

        } else if ( "asynchronous".equals( type ) ) {
            response.setContentType( "text/plain;charset=UTF-8" );
            PrintWriter w = response.getWriter();
            String json = JSON.toJSONString( map );
            w.println( json );
        } else {

        }
    }

}
