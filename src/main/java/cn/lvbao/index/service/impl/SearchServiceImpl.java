package cn.lvbao.index.service.impl;

import cn.lvbao.index.code.ResultEnum;
import cn.lvbao.index.dao.DaoFactory;
import cn.lvbao.index.dao.ESDao;
import cn.lvbao.index.dao.GarbageDao;
import cn.lvbao.index.domain.Garbage;
import cn.lvbao.index.domain.Result;
import cn.lvbao.index.service.SearchService;

import java.io.IOException;
import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-09-24-19:46
 */
public class SearchServiceImpl implements SearchService {
    /**查找服务*/
    private static SearchServiceImpl SEARCH_SERVICE;
    /**查找垃圾的dao*/
    private static GarbageDao garbageDao;
    private static ESDao esDao;
    static{
        SEARCH_SERVICE=new SearchServiceImpl();
        garbageDao= DaoFactory.getGarbageDao();
        esDao=DaoFactory.getEsDao();
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
        if(garbage==null){//如果查找不到,到es中匹配一个近适度高的词
            List<String> list=getWords(keyword);
            if(list==null || list.size()==0){
                result=new Result(ResultEnum.NO_MESSAGE);
            }else{
                result=new Result(ResultEnum.HAVE_MESSAGE,garbageDao.findKeyword(list.get(0)));
            }
        }else {
            result=new Result(ResultEnum.HAVE_MESSAGE,garbage);
        }
        return result;
    }

    @Override
    public Result getPromptWord(String word) {
        //1、es搜得到提示词字符串数组
        List<String> list = getWords(word);
        //2、将数组,长度保存到Result中
        if(list==null || list.size()==0){
            return new Result(ResultEnum.NO_MESSAGE,0,null);
        }else{
            return new Result(ResultEnum.HAVE_MESSAGE,list.size(),list);
        }
    }

    private List<String> getWords(String word) {
        List<String> list;
        try {
            list=esDao.findPromptWord(word);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("流发生错误");
        }
        return list;
    }
}
