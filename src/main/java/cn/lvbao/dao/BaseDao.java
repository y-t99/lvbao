package cn.lvbao.dao;

import cn.lvbao.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author lvbao
 * #create 2019-09-22-19:17
 * Dao类的基本动作
 */
public abstract class BaseDao<T> {
    /**
     * 获取jdbcTemplate
     */
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());
    private BeanPropertyRowMapper<T> bean;
    /**
     * 必须有指定类型,且构造函数只给子类用
     * @param clazz
     */
    protected BaseDao(Class<T> clazz){
        if(clazz==null){
            throw new IllegalArgumentException("参数不能为空");
        }
        bean=new BeanPropertyRowMapper<>(clazz);
    }

    /**
     * sql增删改
     */
    protected  void update(String sql,
                           Object... args){
        template.update(sql,args);
    }

    /**
     * 查找一条记录
     */
    protected  T selectOne(String sql,
                           Object... args){
        try {
            return template.queryForObject(sql, bean, args);
        }catch (EmptyResultDataAccessException e){
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 查找多条数据
     */
    protected List<T> selectList(String sql,
                                 Object... args){
        try {
            return template.query(sql,bean,args);
        } catch (DataAccessException e) {
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *查找符合要求的记录数
     */
    protected  int getCount(String sql,
                            Object... args){
        return template.queryForObject(sql,Integer.class,args);
    }
}

