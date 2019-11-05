package cn.lvbao.index.controllor;

import cn.lvbao.index.domain.ArticleBrief;
import cn.lvbao.index.domain.PageBean;
import cn.lvbao.index.domain.Result;
import cn.lvbao.index.service.PageService;
import cn.lvbao.index.service.ServiceFactory;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanyuan
 * #create 2019-10-13-8:21
 */
@WebServlet("/articleBriefServlet")
public class ArticleBriefServlet extends BaseServlet{
    PageService service= ServiceFactory.getPageService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //0、拿到json对象
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        //1、拿到page信息
        PageBean<ArticleBrief> pageBean = getPage(json);
        pageBean.setCategory((String) json.get("category"));
        //2、把信息交给pageService,得到result
        Result result= service.getArticleBriefs(pageBean, (String) json.get("condition"));
        //3、把result存到req中
        req.setAttribute("result",result);
        super.doPost(req, resp);
    }

    private PageBean<ArticleBrief> getPage(JSONObject json) {
        PageBean<ArticleBrief> pageBean=new PageBean<>();
        pageBean.setCurrentPage((Integer) json.get("currentPage"));
        pageBean.setRows((Integer) json.get("rows"));
        return pageBean;
    }
}
