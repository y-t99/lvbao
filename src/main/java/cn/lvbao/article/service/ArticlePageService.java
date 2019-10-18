package cn.lvbao.article.service;


import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.domain.Result;
import cn.lvbao.index.domain.PageBean;
import cn.lvbao.index.ienum.ConditionEnum;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:52
 */
public interface ArticlePageService {
    /**
     *根据文章id查询文章并封装文章到result中
     */
    Result getAritcle(String id);
    /**
     *根据文章id查找评论
     */
    Result<PageBean<ReviewBean>> getRecord(String id, PageBean<ReviewBean> pageBean, String condition);


}
