package cn.lvbao.percenter.service;

import cn.lvbao.domain.Result;
import cn.lvbao.percenter.domain.LoadMoreResult;
import cn.lvbao.percenter.domain.PerInforBean;
import com.alibaba.fastjson.JSONObject;

/**
 *@Description: 个人中心处理层
 *@Author: ms
 *@Date: 2019/11/12 23:14
 */
public interface PerCenterService {
    //---------个人资料--------
    /**
     * 获取头像
     * @param json
     * @return
     */
    Result getHead(JSONObject json);

    /**
     * 修改头像
     * @param json
     * @return
     */

    Result<Object> modifyHead(JSONObject json);
    /**
     * 获取个人资料
     * @param json
     * @return
     */

    Result<PerInforBean> getPerInfor(JSONObject json);

    /**
     * 编辑个人资料
     * @param json
     * @return
     */
    Result editPerInfor(JSONObject json);

    /**
     * 记录足迹
     * @param json
     * @return
     */
    Result recordTrace(JSONObject json);

    /**
     * 获取足迹
     * @param json
     * @return
     */
    LoadMoreResult getTrace(JSONObject json);

    /**
     * 获取想法
     * @param json
     * @return
     */
    LoadMoreResult getIdea(JSONObject json);

    /**
     * 获取置顶的想法
     * @param json
     * @return
     */
    Result getTopIdea(JSONObject json);

    /**
     * 发布想法
     * @param json
     * @return
     */
    Result issueIdea(JSONObject json);

    /**
     * 把想法置顶
     * @param json
     * @return
     */
    Result stickIdea(JSONObject json);

    /**
     * 取消想法的置顶
     * @param json
     * @return
     */
    Result cancelSticking(JSONObject json);

    /**
     * 获取发布过的文章
     * @param json
     * @return
     */
    Result getIssuedArticle(JSONObject json);
}

