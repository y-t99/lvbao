package cn.lvbao.index.dao;

import cn.lvbao.index.code.ConditionEnum;
import cn.lvbao.index.domain.ArticleBrief;
import cn.lvbao.index.domain.PageBean;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-13-17:20
 */
public interface ArticleBriefDao {
    /**
     * 查找文章简介数量
     */
    int findArticleBriefCount();
    /**
     * 按条件查找文章简介列表
     */
    List<ArticleBrief> findListByCondition(PageBean pageBean, ConditionEnum condition);
}
