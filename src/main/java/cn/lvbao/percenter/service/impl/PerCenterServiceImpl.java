package cn.lvbao.percenter.service.impl;

import cn.lvbao.code.ResultEnum;
import cn.lvbao.domain.PageBean;
import cn.lvbao.domain.Result;
import cn.lvbao.percenter.dao.PerCenterDao;
import cn.lvbao.percenter.dao.impl.PerCenterDaoImpl;
import cn.lvbao.percenter.domain.*;
import cn.lvbao.percenter.service.PerCenterService;
import cn.lvbao.util.CommonUtils;
import com.alibaba.fastjson.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *@Description:
 *@Author: ms
 *@Date: 2019/11/12 23:19
 */
public class PerCenterServiceImpl implements PerCenterService {

    private PerCenterDao dao = new PerCenterDaoImpl();
    /**
     * 想法置顶的上限条数为6
     */
    private final int TOP_LIMIT = 6;

//    ----------头像---------------

    @Override
    public Result getHead(JSONObject json) {
        Result<String> result = null;
        //1、获取用户id
        String userID = json.getString("userID");
        //2、查询头像,封装结果
        String headUrl = dao.queryHead(userID);
        if (headUrl != null) {
            result = new Result<>(ResultEnum.HAVE_MESSAGE, "请求头像成功");
            result.setData(headUrl);
        } else {
            result = new Result<>(ResultEnum.NO_MESSAGE, "请求头像失败");
        }
        //3、返回结果
        return result;
    }

    @Override
    public Result<Object> modifyHead(JSONObject json) {
        Result result = null;
        //1、json获取用户id，新头像路径
        String userID=json.getString("userID");
        String headUrl=json.getString("headUrl");
        //2、dao层操作更新数据
        if (dao.updateHead(userID, headUrl)) {
            result = new Result(ResultEnum.HAVE_MESSAGE, "修改头像成功");
        } else {
            result = new Result(ResultEnum.NO_MESSAGE, "修改头像失败");
        }
        //3、返回结果
        return result;
    }

//    ----------个人资料---------------

    @Override
    public Result<PerInforBean> getPerInfor(JSONObject json) {
        Result<PerInforBean> result = null;
        //1、获取用户id
        String userID = json.getString("userID");
        //2、根据id查询个人资料
        PerInforBean perInforBean = dao.queryPerInfor(userID);
        if (perInforBean != null) {
            result = new Result(ResultEnum.HAVE_MESSAGE, "成功");
            result.setData(perInforBean);
        } else {
            result = new Result<>(ResultEnum.NO_MESSAGE, "请求不到个人资料！");
        }
        //3、返回结果
        return result;
    }

    @Override
    public Result editPerInfor(JSONObject json) {
        Result result = null;
        //1、json转化
        PerInforBean perInforBean = json.toJavaObject(PerInforBean.class);
        //2、把资料信息对象存到数据库
        if (dao.updatePerInfor(perInforBean)) {
            result = new Result(ResultEnum.HAVE_MESSAGE, "编辑资料成功！");
        } else {
            result = new Result(ResultEnum.NO_MESSAGE, "编辑资料失败！");
        }
        //3、返回结果
        return result;
    }


//    ----------足迹---------------

    @Override
    public Result recordTrace(JSONObject json) {
        //记录足迹 的结果
        Result result = null;
        //数据库操作的结果
        boolean end = false;
        //1、转换json对象
        TraceBean trace = json.toJavaObject(TraceBean.class);
        String userID = trace.getUserID();
        String articleID = trace.getArticleID();
        //2、查询该用户是否浏览过该文章
        if (dao.isExistedTrace(trace)) {
            //3、如果浏览过，只需更新时间
            end = dao.updateSkimTime(trace);
        } else {
            //4、如果没有浏览过，则写入数据库
            end = dao.insertTrace(trace);
        }
        //5、封装结果并返回
        if (end) {
            result = new Result(ResultEnum.HAVE_MESSAGE, "记录足迹成功");
        } else {
            result = new Result(ResultEnum.NO_MESSAGE, "记录足迹失败");
        }
        return result;
    }

    @Override
    public LoadMoreResult getTrace(JSONObject json) {
        LoadMoreResult result = null;
        //1、json转化，获取id、请求的条数和已加载条数
        String userID = json.getString("userID");
        int rowNum = json.getInteger("rowNum");
        int loadedRows = json.getInteger("loadedRows");
        //2、查询数据库
        List<TraceBean> traces = dao.queryTraces(userID, loadedRows, rowNum);
        //3、处理结果——按时间分组
        if (traces == null) {
            result = new LoadMoreResult(ResultEnum.NO_MESSAGE, "无记录");
            return result;
        }
        List<DailyRecordBean<TraceBean>> list = new ArrayList<>();
        int count = traces.size();
        int i = 0;
        //4、遍历查到的所有记录
        while (i < count) {
            Date day = traces.get(i).getSkimDay();
            DailyRecordBean<TraceBean> dailyRecordBean = new DailyRecordBean<>();
            dailyRecordBean.setRecords(new ArrayList<>());
            int j = i;
            //5、把日期一致的放到同一个对象（同一个列表）
            while (j < count && traces.get(j).getSkimDay().equals(day)) {
                dailyRecordBean.getRecords().add(traces.get(j++));
            }
            i = j;
            //6、一个日期的所有记录封装完毕，记录日期并把该对象放入结果列表
            dailyRecordBean.setDate(day);
            list.add(dailyRecordBean);
        }
        //7、封装结果并返回
        result = new LoadMoreResult(ResultEnum.HAVE_MESSAGE, "查询到记录");
        result.setData(list);
        result.setTotalRows(dao.getTotalCount(userID, "trace"));
        result.setLoadedRows(loadedRows + count);
        return result;
    }

//    ----------想法---------------

    @Override
    public LoadMoreResult getIdea(JSONObject json) {
        LoadMoreResult result = null;
        //1、json转化，获取id、请求的条数和已加载条数
        String userID = json.getString("userID");
        int rowNum = json.getInteger("rowNum");
        int loadedRows = json.getInteger("loadedRows");
        //2、查询数据库
        List<IdeaBean> ideas = dao.queryIdeas(userID, loadedRows, rowNum);
        //3、处理结果——按时间分组
        if (ideas == null) {
            result = new LoadMoreResult(ResultEnum.NO_MESSAGE, "无记录");
            return result;
        }
        List<DailyRecordBean<IdeaBean>> list = new ArrayList<>();
        int count = ideas.size();
        int i = 0;
        //4、遍历查到的所有记录
        while (i < count) {
            Date day = ideas.get(i).getIdeaSdDay();
            DailyRecordBean<IdeaBean> dailyRecordBean = new DailyRecordBean<>();
            dailyRecordBean.setRecords(new ArrayList<>());
            int j = i;
            //5、把日期一致的放到同一个对象（同一个列表）
            while (j < count && ideas.get(j).getIdeaSdDay().equals(day)) {
                dailyRecordBean.getRecords().add(ideas.get(j++));
            }
            i = j;
            //6、一个日期的所有记录封装完毕，记录日期并把该对象放入结果列表
            dailyRecordBean.setDate(day);
            list.add(dailyRecordBean);
        }
        //7、封装结果并返回
        result = new LoadMoreResult(ResultEnum.HAVE_MESSAGE, "查询到记录");
        result.setData(list);
        result.setTotalRows(dao.getTotalCount(userID, "idea"));
        result.setLoadedRows(loadedRows + count);
        return result;
    }

    @Override
    public Result getTopIdea(JSONObject json) {
        Result result = null;
        //1、获取json存放的用户id
        String userID = json.getString("userID");
        //2、根据id查询数据库记录
        List<IdeaBean> topIdeas = dao.queryTopIdeas(userID);
        //3、返回结果
        if (topIdeas == null) {
            result = new Result(ResultEnum.NO_MESSAGE, "查无置顶想法记录");
        } else {
            result = new Result(ResultEnum.HAVE_MESSAGE, "成功");
            result.setData(topIdeas);
        }
        return result;
    }

    @Override
    public Result issueIdea(JSONObject json) {
        Result result = null;
        //1、json转化，添加id
        IdeaBean idea = json.toJavaObject(IdeaBean.class);
        String ideaID = CommonUtils.uuid();
        idea.setIdeaId(ideaID);
        //2、把想法和作者id存入数据库
        if (dao.insertIdea(idea)) {
            result = new Result(ResultEnum.HAVE_MESSAGE, "记录想法成功");
        } else {
            result = new Result(ResultEnum.NO_MESSAGE, "记录想法失败");
        }
        //3、返回结果
        return result;
    }

    @Override
    public Result stickIdea(JSONObject json) {
        Result result = null;
        //1、转化json
        IdeaBean ideaBean = json.toJavaObject(IdeaBean.class);
        //2、查询用户已置顶的条数
        int topNum = dao.getIdeaTopNum(ideaBean.getIdeaMasterID());
        //3、判断是否已达到规定的上限
        if (topNum >= TOP_LIMIT) {
            //4、达到上限返回反馈结果
            result = new Result(ResultEnum.NO_MESSAGE, "顶不住了！您最多只能置顶6条想法哦！");
        } else {
            //5、未达到上限，把想法对象置顶状态更新为1（是）
            if (dao.updateIsTop(ideaBean.getIdeaId(), 1)) {
                result = new Result(ResultEnum.HAVE_MESSAGE, "成功");
            } else {
                result = new Result(ResultEnum.NO_MESSAGE, "failed");
            }
        }
        //3、返回结果
        return result;
    }

    @Override
    public Result cancelSticking(JSONObject json) {
        Result result = null;
        //1、转化json
        IdeaBean ideaBean = json.toJavaObject(IdeaBean.class);
        //2、把想法对象置顶状态更新为0（否）
        if (dao.updateIsTop(ideaBean.getIdeaId(), 0)) {
            result = new Result(ResultEnum.HAVE_MESSAGE, "成功");
        } else {
            result = new Result(ResultEnum.NO_MESSAGE, "failed");
        }
        //3、返回结果
        return result;
    }

    //    ----------发布的作品---------------
    @Override
    public Result getIssuedArticle(JSONObject json) {
        Result result = null;
        //1、获取json用户id，请求的记录条数和页码
        String userID = json.getString("userID");
        int rows = (int) json.get("rows");
        int currentPage=(int)json.get("currentPage");
        //2、根据用户id查询文章
        List<IssuedArticleBean> articles = dao.queryArticleByPage(userID, (currentPage-1)*rows, rows);
        if (articles != null) {
            PageBean<IssuedArticleBean> pageBean = new PageBean<>();
            pageBean.setCurrentPage(currentPage);
            pageBean.setList(articles);
            pageBean.setRows(articles.size());
            //3、查询总数，计算总页数
            int total=dao.getTotalCount(userID, "issued");
            pageBean.setTotalCount(total);
            pageBean.setTotalPage(total/rows+1);
            result = new Result(ResultEnum.HAVE_MESSAGE, "成功");
            result.setData(pageBean);
        } else {
            result = new Result(ResultEnum.NO_MESSAGE, "失败");
        }
        //4、返回结果
        return result;
    }

}