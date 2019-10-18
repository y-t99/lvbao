package cn.lvbao.article.controllor;

import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.article.service.ArticlePageService;
import cn.lvbao.article.service.ServiceFactory;
import cn.lvbao.domain.Result;
import cn.lvbao.index.domain.ArticleBrief;
import cn.lvbao.index.domain.PageBean;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:41
 * 文章静态内容呈现
 */
@WebServlet("/articlePageServlet")
public class ArticlePageServlet extends BaseServlet{
    private ArticlePageService service= ServiceFactory.getArticlePageService();
    /**
     * 文章主体区
     * @param req
     * @param resp
     */
    public void articleBody(HttpServletRequest req, HttpServletResponse resp){
        //1、取出json对象
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        //2、根据文章id取得文章,得到result
        Result result = service.getAritcle((String) json.get("id"));
        //3、将result封装到req中
        req.setAttribute("result",result);
    }

    /**
     * 留言区
     * @param req
     * @param resp
     */
    public void record(HttpServletRequest req, HttpServletResponse resp){
        //0、取出json对象
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        //1、获得页面信息
        PageBean<ReviewBean> pageBean=getPageBean(json);
        //2、根据文章id取得评论,得到result
        Result<PageBean<ReviewBean>> result=service.getRecord((String)json.get("id"),pageBean,(String) json.get("condition"));
        //3、将result封装到req中
        req.setAttribute("result",result);
    }

    private PageBean<ReviewBean> getPageBean(JSONObject json) {
        PageBean<ReviewBean> pageBean=new PageBean<>();
        pageBean.setCurrentPage((Integer) json.get("currentPage"));
        pageBean.setRows((Integer) json.get("rows"));
        return pageBean;
    }

    /**
     * 博主信息
     * @param req
     * @param resp
     */
    public void master(HttpServletRequest req, HttpServletResponse resp){

    }
}
