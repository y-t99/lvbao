package cn.lvbao.index.service.impl;

import cn.lvbao.index.code.ResultEnum;
import cn.lvbao.index.dao.GarbageDao;
import cn.lvbao.index.dao.impl.GarbageDaoImpl;
import cn.lvbao.index.domain.Garbage;
import cn.lvbao.index.domain.Result;
import cn.lvbao.index.service.SearchService;

/**
 * @author yuanyuan
 * #create 2019-09-24-19:46
 */
public class SearchServiceImpl implements SearchService {
    /**查找服务*/
    private static SearchServiceImpl SEARCH_SERVICE;
    /**查找垃圾的dao*/
    private static GarbageDao garbageDao;
    static{
        SEARCH_SERVICE=new SearchServiceImpl();
        garbageDao= GarbageDaoImpl.getInstance();
    }
    private SearchServiceImpl(){

    }
    public static SearchServiceImpl getInstance(){
        return SEARCH_SERVICE;
    }


    @Override
    public Result getKeyMsg(String keyword) {
        //1、数据库条件查询
        Garbage garbage = garbageDao.findKeyword(keyword);
        Result result=null;
        //2、判断garbage是否为空
        if(garbage==null){
            result=new Result(ResultEnum.NO_MESSAGE);
        }else {
            result=new Result(ResultEnum.HAVE_MESSAGE,garbage);
        }
        return result;
    }
}
