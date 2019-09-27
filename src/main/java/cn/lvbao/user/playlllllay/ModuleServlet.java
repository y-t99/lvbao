package cn.lvbao.user.playlllllay;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/module/*")
public class ModuleServlet extends BaseServlet {
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("mifaf1发的");
    }

    protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
