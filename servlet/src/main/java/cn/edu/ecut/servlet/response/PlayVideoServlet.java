package cn.edu.ecut.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet( "/play/video" )
public class PlayVideoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        final String location = "D:/videos" ;
        final String name = "浏览器支持的视频.mp4" ;

    }

}
