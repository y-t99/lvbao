package cn.lvbao.index.dao.impl;

import cn.lvbao.index.dao.BaseDao;
import cn.lvbao.index.dao.GarbageDao;
import cn.lvbao.index.domain.Garbage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-09-24-22:27
 */
public class GarbageDaoImpl extends BaseDao<Garbage> implements GarbageDao {

//——————————————————————————单例模式开始——————————————————————————//
    private static GarbageDaoImpl GARBAGE_DAO;
    static {
        GARBAGE_DAO=new GarbageDaoImpl();
    }
    private GarbageDaoImpl(){
        super(Garbage.class);
    }
    public static GarbageDaoImpl getInstance(){
        return GARBAGE_DAO;
    }
//——————————————————————————单例模式结束——————————————————————————//

//——————————————————————————查找垃圾开始——————————————————————————//
    @Override
    public Garbage findKeyword(String keyword) {
        //1、查找关键词
        String sql=" SELECT * FROM " +
                " keyhouse,deschouse " +
                " WHERE keyhouse.k_id=deschouse.d_id " +
                " and keyword=?";
        Map<String, Object> map = selectOneToMap(sql, keyword);
        //2、判断是否有纪律
        if (map==null){
            return null;
        }
        //3、将记录封装为垃圾对象
        Garbage garbage=new Garbage();
        garbageWrap(garbage,map);
        return garbage;
    }

    /**
     * 把map的信息封装到垃圾实体中
     * @param garbage
     * @param map
     */
    private void garbageWrap(Garbage garbage, Map<String, Object> map) {

        garbage.setName((String) map.get("keyword"));

        garbage.setCategory((String) map.get("category"));

        //1、把父类字段封装成垃圾对象
        Garbage parent=new Garbage();
        parent.setName((String) map.get("parent"));
        garbage.setParent(parent);

        //2、获取子类们
        List<Garbage> childrens=getChildrens((String) map.get("keyword"));
        garbage.setChildrens(childrens);

        garbage.setInfo((String) map.get("info"));
    }

    /**
     * 得到子类对象
     * @param keyword
     * @return
     */
    private List<Garbage> getChildrens(String keyword) {
        //1、查询父类是对象的记录
        String sql="SELECT keyword FROM " +
                " keyhouse,deschouse " +
                " WHERE keyhouse.k_id=deschouse.d_id " +
                " and parent=? " +
                " LIMIT 0,10 ";
        List<Map<String,Object>> maps=selectListToMap(sql,keyword);
        //2、判断是否有记录
        if (maps.isEmpty()){
            return null;
        }
        //3、将每天记录封装为垃圾类对象
        ArrayList<Garbage> childrens = new ArrayList<>();
        Garbage garbage;
        for(Map<String,Object> map:maps){
            garbage= new Garbage();
            garbage.setName((String) map.get("keyword"));
            childrens.add(garbage);
        }
        return childrens;
    }
//——————————————————————————查找垃圾结束——————————————————————————//

}
