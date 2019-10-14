package cn.lvbao.index.service.impl;

import cn.lvbao.index.ienum.ConditionEnum;
import cn.lvbao.code.ResultEnum;
import cn.lvbao.index.dao.ArticleBriefDao;
import cn.lvbao.index.dao.DaoFactory;
import cn.lvbao.index.domain.ArticleBrief;
import cn.lvbao.index.domain.PageBean;
import cn.lvbao.index.domain.Result;
import cn.lvbao.index.service.PageService;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-13-8:24
 */
public class PageServiceImpl implements PageService {
    private static PageServiceImpl PAGE_SERVICE;
    private PageServiceImpl(){
    }
    static {
        PAGE_SERVICE=new PageServiceImpl();
    }
    public static PageService getInstance() {
        return PAGE_SERVICE;
    }

    private ArticleBriefDao dao= DaoFactory.getArticleBriefDao();

    @Override
    public Result getArticleBriefs(PageBean<ArticleBrief> pageBean, String condition) {
        //1、补全页面信息
        fillPage(pageBean);
        //2、根据分页信息,获得文章简介对象列表
        List<ArticleBrief> list=null;
        if("time".equals(condition)) {
            list= dao.findListByCondition(pageBean, ConditionEnum.TIME);
        }else if ("start".equals(condition)){
            list= dao.findListByCondition(pageBean, ConditionEnum.Start);
        }
        pageBean.setList(list);
        //3、page写好存到result中
        Result result=new Result(ResultEnum.HAVE_MESSAGE,pageBean);
        return result;
    }

    /**
     * 补全页面信息
     * @param pageBean
     */
    private void fillPage(PageBean<ArticleBrief> pageBean) {
        //得到纪律总数
        int totalCount=dao.findArticleBriefCount();
        pageBean.setTotalCount(totalCount);
        int rows=pageBean.getRows();
        //对纪律/页、当前页数进行判断 不合法修改
        if(rows<1){
            rows=10;//默认10
            pageBean.setRows(rows);
        }
        //得到纪律总页数
        int totalPage=totalCount%rows==0?totalCount/rows:totalCount/rows+1;
        pageBean.setTotalPage(totalPage);
        int currentPage=pageBean.getCurrentPage();
        if(currentPage<1){
            currentPage=1;
        } else if (currentPage>totalCount){
            currentPage=totalCount;
        }
        pageBean.setCurrentPage(currentPage);
    }
}
