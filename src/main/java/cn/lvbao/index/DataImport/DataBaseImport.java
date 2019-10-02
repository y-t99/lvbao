package cn.lvbao.index.DataImport;

import cn.lvbao.util.JDBCUtils;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;

/**
 * @author yuanyuan
 * #create 2019-09-30-22:47
 */
public class DataBaseImport {
    /**
     *     1、流读取csv文件
     *     2、一行一行读取
     *     3、将每行按","分隔成一个字符串数组
     *     4、写入数据库
     */
//    @Test
    private void run() throws IOException {
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());//获取sql执行工具
        String sql=" INSERT INTO " +
                " keyhouse(keyword,parent,k_id,category) " +
                " VALUES(?,?,?,?)";
        //1、读取csv文件
        BufferedReader reader=new BufferedReader(new FileReader(new File("d://product.csv")));
        //  舍弃投信息
        reader.readLine();
        String line=null;
        //2、读取每一行
        while((line=reader.readLine())!=null){
            //3、将信息分隔
            String[] strings = line.split(",");
            //  提取有用信息
            String keyword=strings[1];//1.1 垃圾名称
            String category;
            int k_id;//1.2 分类描述信息
            switch (strings[3]){//1.3 提取分类
                case "1":
                    category="可回收垃圾";
                    k_id=1;
                    break;
                case "2":
                    category="有害垃圾";
                    k_id=2;
                    break;
                case "3":
                    category="湿垃圾";
                    k_id=3;
                    break;
                case "4":
                    category="干垃圾";
                    k_id=4;
                    break;
                default:
                    category=null;
                    k_id=0;
            }
            //4、插入数据库
            try {
                template.update(sql,keyword,category,k_id,category);
            } catch (DataAccessException e) {
                /*
                 * 艾草、面条、话梅壳、面条、鲜花、油条、枣核、
                 * 药瓶、奶茶盖、面膜、塑料袋、粘鼠板、办公用纸、
                 * 报纸、包装纸、复印纸、广告纸片、购物卡、蜡烛、
                 * 食品罐头、消毒液瓶、硬纸板、纸、纸盒、泡沫板
                 */
            }
        }
    }
}
