package cn.lvbao.percenter.dao;

import cn.lvbao.percenter.domain.IdeaBean;
import cn.lvbao.percenter.domain.IssuedArticleBean;
import cn.lvbao.percenter.domain.PerInforBean;
import cn.lvbao.percenter.domain.TraceBean;

import java.util.List;
import java.util.Map;

/**
 *@Description: 个人中心数据操作层
 *@Author: ms
 *@Date: 2019/11/14 23:58
 */
public interface PerCenterDao {
    /**
     * 插入足迹记录
     * @param trace
     * @return
     */
    boolean insertTrace(TraceBean trace);

    /**
     * 查询是否存在浏览记录
     * @param trace
     * @return
     */
    boolean isExistedTrace(TraceBean trace);

    /**
     * 更新足迹的时间
     * @param trace
     * @return
     */
    boolean updateSkimTime(TraceBean trace);

    /**
     * 获取头像路径
     * @param userID
     * @return
     */
    String queryHead(String userID);

    /**
     * 修改头像
     * @param userID
     * @param headUrl
     * @return
     */
    boolean updateHead(String userID,String headUrl);

    /**
     * 查询个人信息
     * @param userID
     * @return
     */
    PerInforBean queryPerInfor(String userID);

    /**
     * 编辑/修改个人信息
     * @param perInforBean
     * @return
     */
    boolean updatePerInfor(PerInforBean perInforBean);

    /**
     * 查询足迹
     * @param userID
     * @param rowNum
     * @return
     */
    List<TraceBean> queryTraces(String userID,int start,int rowNum);

    /**
     * 添加想法
     * @param idea
     * @return
     */
    boolean insertIdea(IdeaBean idea);

    /**
     * 查询想法
     * @param userID
     * @return
     */
    List<IdeaBean> queryIdeas(String userID,int start,int rowNum);

    /**
     * 查询置顶的想法
     * @param userID
     * @return
     */
    List<IdeaBean> queryTopIdeas(String userID);

    /**
     * 修改想法的置顶状态
     * @param ideaID
     * @param isTop  想法的新置顶状态
     * @return
     */
    boolean updateIsTop(String ideaID,int isTop);

    /**
     * 查询用户已置顶的想法条数
     * @param userID
     * @return
     */
    int getIdeaTopNum(String userID);

    /**
     * 查询发布过的作品（文章）
     * @param userID
     * @param rows
     * @return
     */
    List<IssuedArticleBean> queryArticleByPage(String userID, int start, int rows);

    /**
     * 根据用户id查询对应数据的条数
     * @param userID
     * @param dataName
     * @return
     */
    int getTotalCount(String userID,String dataName);
}

