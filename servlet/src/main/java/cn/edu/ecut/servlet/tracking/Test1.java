package cn.edu.ecut.servlet.tracking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/track/asd")
public class Test1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(resp.containsHeader("cookie"));
        Cookie cookie=new Cookie("username","zhangsanfeng");
        System.out.println(resp.containsHeader("cookie"));
        resp.addCookie(cookie);
        System.out.println(resp.containsHeader("cookie"));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        Collection<String> collection=resp.getHeaderNames();
        for(String name:collection){
            Collection<String> value=resp.getHeaders(name);
            System.out.println(name+"="+value);
        }
        resp.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter w=resp.getWriter();
        w.println("<div>查看浏览器</div>");
    }

}