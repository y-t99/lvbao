package yt.dao;

import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-09-25-23:04
 */
public class DaoTest {
    public static void main(String[] args) {
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());
        String sql=" SELECT * FROM " +
                " keyhouse,deschouse " +
                " WHERE keyhouse.descID=deschouse.descID " +
                " and keyword=?";
        Map<String, Object> map = template.queryForMap(sql, "可回收垃圾");
        for(String key:map.keySet()){
            System.out.println(map.get(key));
        }
    }
}
