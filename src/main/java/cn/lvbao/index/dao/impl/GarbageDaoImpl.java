package cn.lvbao.index.dao.impl;

import cn.lvbao.index.dao.BaseDao;
import cn.lvbao.index.dao.GarbageDao;
import cn.lvbao.index.domain.Garbage;

import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-09-24-22:27
 */
public class GarbageDaoImpl extends BaseDao<Garbage> implements GarbageDao {
    /**
     * 单例模式
     */
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


    @Override
    public Garbage findKeyword(String keyword) {
        String sql=" SELECT * FROM " +
                " keyhouse,deschouse " +
                " WHERE keyhouse.descID=deschouse.descID " +
                " and keyword=?";
        Map<String, Object> map = selectOneToMap(sql, keyword);
        if (map==null){
            return null;
        }
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
        Garbage parent=new Garbage();
        parent.setName((String) map.get("parent"));
        garbage.setParent(parent);
        garbage.setChildrens(null);
        garbage.setInfo((String) map.get("info"));
    }
}
