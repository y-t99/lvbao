package cn.lvbao.idea.service.impl;

import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.code.ResultEnum;
import cn.lvbao.domain.Result;
import cn.lvbao.idea.dao.IdeaDao;
import cn.lvbao.idea.dao.impl.IdeaDaoImpl;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;
import cn.lvbao.idea.service.IdeaService;
import cn.lvbao.util.CommonUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@Description: 创意模块逻辑实现
 *@Author: ms
 *@Date: 2019/10/26 22:01
 */
public class IdeaServiceImpl implements IdeaService {

    private IdeaDao ideaDao = new IdeaDaoImpl();
    private Stack<VideoBean> videoStack=new Stack<VideoBean>();

    public IdeaServiceImpl(){
        //初始化时把视频压栈
        pressStack();
    }

    private void pressStack(){
        try{
            List<VideoBean> videos=ideaDao.getVideos();
            for(VideoBean v:videos){
                videoStack.push(v);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Result<VideoBean> getVideoUrl() {
        Result<VideoBean> result=new Result<VideoBean>();
        VideoBean video=null;
        //1、判空存放视频的栈
        if(!videoStack.isEmpty()){
            //2、非空则弹出
            video=videoStack.pop();
        } else {
            //3、为空则重新压栈
            pressStack();
            video=videoStack.pop();
        }
        //4、封装结果并返回
        if(video!=null){
            result.setCode(200);
            result.setMsg("成功");
        } else {
            result.setCode(402);
            result.setMsg("获取视频失败");
        }
        result.setData(video);
        return result;
    }

    @Override
    public Result<List<CategoryBean>> getCategory() {
        Result<List<CategoryBean>> result = new Result<List<CategoryBean>>();
        List<CategoryBean> firstCate=new ArrayList<>();
        try{
            //1、先查询一级目录
            firstCate = ideaDao.getFirstCate();
            //2、遍历每个一级目录项查询并填充其二级目录
            for(CategoryBean cate:firstCate){
                if("话题".equals(cate.getName())){
                    List<CategoryBean> types = ideaDao.getTypes();
                    System.out.println(types);
                    cate.setChildren(types);
                } else {
                    List<CategoryBean> children = ideaDao.getSecondCate(cate);
                    cate.setChildren(children);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        //3、返回结果
        if(!firstCate.isEmpty()){
            result.setCode(200);
            result.setMsg("成功");
        } else {
            result.setCode(402);
            result.setMsg("获取菜单目录失败！");
        }
        result.setData(firstCate);
        return result;
    }

    @Override
    public Result issueArticle(JSONObject json) {
        System.out.println("service issue");
        Result result = null;
        //1、转化json，生成随机id号
        ArticleBean articleBean = json.toJavaObject(ArticleBean.class);
        articleBean.setId(CommonUtils.uuid());
        //2、把文章对象存入数据库
        if(ideaDao.addArticle(articleBean)){
            result = new Result(ResultEnum.HAVE_MESSAGE,"发布成功");
        } else {
            result = new Result(ResultEnum.NO_MESSAGE,"发布失败");
        }
        //3、返回结果
        return result;
    }
}

