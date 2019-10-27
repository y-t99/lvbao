package cn.lvbao.article.service.impl;

import cn.lvbao.article.dao.ArticleDao;
import cn.lvbao.article.dao.DaoFactory;
import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.article.service.ArticlePageService;
import cn.lvbao.code.ResultEnum;
import cn.lvbao.domain.Result;
import cn.lvbao.index.domain.PageBean;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:54
 */
public class ArticlePageServiceImpl implements ArticlePageService {
    private ArticleDao dao= DaoFactory.getArticleDao();
    private static ArticlePageServiceImpl ARTICLE_PAGE_SERVICE;
    private ArticlePageServiceImpl(){

    }
    static{
        ARTICLE_PAGE_SERVICE=new ArticlePageServiceImpl();
    }
    public static ArticlePageServiceImpl getInstance(){
        return ARTICLE_PAGE_SERVICE;
    }

    @Override
    public Result getAritcle(String id) {
        //查询数据库得到文章对象
        ArticleBean article=dao.findArticle(id);
        //把文章对象放到result中
        Result<ArticleBean> result=new Result<>();
        if(article!=null){
            result.setCode(ResultEnum.HAVE_MESSAGE.getCode());
            result.setData(article);
            result.setMsg("文章找寻成功");
        }else{
            result.setCode(ResultEnum.NO_MESSAGE.getCode());
            result.setMsg("没有找到相关文章");
        }
        return result;
    }

    @Override
    public Result<PageBean<ReviewBean>> getRecord(String id, PageBean<ReviewBean> pageBean, String condition) {
        //1、对PageBean页面信息进行补全
        fillPage(id, pageBean);
        //2、dao获取list
        List<ReviewBean> list=dao.findReviewsList(id,pageBean,condition);
        pageBean.setList(list);
        //3、将PageBean存到result并返回
        Result<PageBean<ReviewBean>> result=new Result<>();
        if (list==null){
            result.setCode(402);
            result.setMsg("发生错误");
        }else{
            result.setCode(200);
            result.setMsg("留言数据返回");
            result.setData(pageBean);
        }
        return result;
    }

    @Override
    public Result<Object> star(String starType,String id, String userID) {
        Result result=new Result();

        //有异常返回200
        try {
            if (starType.equals("articleStar")) {
                //1、dao进行计数
                dao.addArticleStar(id);//文章点赞数增加一
                //2、保存点赞信息
                dao.saveArticleStarMsg(id, userID);//点赞文章和用户id绑定
            }else if (starType.equals("reviewStar")){
                dao.addReviewStar(id);//回复点赞加一
                dao.saveReviewStarMsg(id, userID);//点赞回复和用户id绑定
            }else if(starType.equals("replyStar")){
                dao.addReplyStar(id);
                dao.saveReplyStarMas(id,userID);
            }
            //3、点赞成功
            result.setCode(200);
            result.setMsg("点赞成功");
        }catch (Exception e){
            result.setCode(402);
            result.setMsg("点赞失败");
        }
        return result;
    }

    @Override
    public void saveStarCount() {
        dao.saveStarCount();
    }

    @Override
    public void saveStarBand() {
        dao.saveStarBand();
    }

    private void fillPage(String id, PageBean<ReviewBean> pageBean) {
        // 获得文章评论总记录数
        int totalCount=dao.getReviewCount(id);
        // 计算页数
        int rows=pageBean.getRows();
        if(rows<0){
            rows=10;
        }
        int totalPage=totalCount%rows==0?totalCount/rows:totalCount/rows+1;
        // 判断当前页数的合法性
        int currentPage=pageBean.getCurrentPage();
        if(currentPage<0 || currentPage>totalCount){
            currentPage=1;
        }
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setRows(rows);
        pageBean.setCurrentPage(currentPage);
    }
}
