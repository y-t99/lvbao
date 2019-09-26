package cn.lvbao.controllor;

import cn.lvbao.index.domain.Result;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lvbao
 * #create 2019-09-22-20:51
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Result result = (Result) req.getAttribute("result");
        PrintWriter out = resp.getWriter();
        if (result != null) {
            out.write(JSONObject.toJSONString(result));
            out.flush();
            out.close();
        }
    }
}
