package cn.lvbao.index.service;

import cn.lvbao.index.code.ConditionEnum;
import cn.lvbao.index.domain.ArticleBrief;
import cn.lvbao.index.domain.PageBean;
import cn.lvbao.index.domain.Result;

/**
 * @author yuanyuan
 * #create 2019-10-13-8:23
 */
public interface PageService {
    /**
     * 获取文章简介信息
     * @param pageBean
     * @param condition 情况
     * @return
     */
    Result getArticleBriefs(PageBean<ArticleBrief> pageBean, String condition);
}
