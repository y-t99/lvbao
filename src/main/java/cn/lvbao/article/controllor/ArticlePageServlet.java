package cn.lvbao.article.controllor;

import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.article.service.ArticlePageService;
import cn.lvbao.article.service.ServiceFactory;
import cn.lvbao.domain.Result;
import cn.lvbao.index.domain.PageBean;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String userID=(String) json.get("userID");
        String id=(String)json.get("id");
        Result result=service.getAritcle(id,userID);
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
        Result<PageBean<ReviewBean>> result=service.getRecord((String)json.get("id"),pageBean,(String) json.get("condition"),(String) json.get("userID"));
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
    /**
     * 点赞
     */
    public void giveStar(HttpServletRequest req,HttpServletResponse resp){
        //1、获取json对象
        JSONObject json=(JSONObject)req.getAttribute("requestBody");
        //2、将文章id号和用户id号出入service
        Result<Object> result=service.star((String)json.get("starType"),(String)json.get("id"),(String)json.get("userID"));
        //3、返回成功给前端
        req.setAttribute("result",result);
    }

    /**
     * 评论
     */
    public void comment(HttpServletRequest req,HttpServletResponse resp){
        //1、提取评论内容,之前有req加强
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        //2、评论存储
        Result<Object> result=service.saveReview((String)json.get("review"),(String)json.get("user"),(String)json.get("article"));
        //3、返回结果
        req.setAttribute("result",result);
    }

    @Override
    public void destroy() {
        //将缓存写入数据库中
        //1、点赞表
        service.saveStarCount();
        //2、绑定表
        service.saveStarBand();
    }
}
