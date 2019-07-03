package cn.edu.ecut.servlet.tracking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * 获取session对象
 */
@WebServlet("/session/get")
public class Test2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(resp.containsHeader("set-cookie"));
        //获取当前请求关联的HttpSession对象
        HttpSession session=req.getSession();
        System.out.println(resp.containsHeader("set-cookie"));

        Collection<String> collection=resp.getHeaders("set-cookie");
        for(String s:collection){
            System.out.println(s);
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter w=resp.getWriter();
        w.println("<h1 align=center>"+session.getId()+"</h1>");
    }
}