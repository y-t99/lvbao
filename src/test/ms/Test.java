package ms;

import cn.lvbao.code.ResultEnum;
import cn.lvbao.domain.Result;
import cn.lvbao.idea.dao.IdeaDao;
import cn.lvbao.idea.dao.impl.IdeaDaoImpl;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;
import cn.lvbao.idea.service.IdeaService;
import cn.lvbao.idea.service.impl.IdeaServiceImpl;
import cn.lvbao.percenter.domain.*;
import cn.lvbao.percenter.service.PerCenterService;
import cn.lvbao.percenter.service.impl.PerCenterServiceImpl;
import cn.lvbao.user.dao.DaoFactory;
import cn.lvbao.user.dao.UserDao;
import cn.lvbao.user.domain.User;
import cn.lvbao.util.CommonUtils;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 各种id生成策略
 */
class IDUtils {


    /**
     * 图片名生成
     */
    public static String genImageName() {
//取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
//long millis = System.nanoTime();
//加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
//如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 商品id生成
     */
    public static long genItemId() {
//取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
//long millis = System.nanoTime();
//加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
//如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for(int i=0;i< 100;i++)
            System.out.println(genItemId());
    }
}


/**
 * @author yuanyuan
 * #create 2019-09-26-12:37
 */

public class Test {

    @org.junit.Test
    public void test1(){

        String tabName="lvbao_video";
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());
//1、查询
        String sql="SELECT * FROM "+tabName;
        List<Map<String,Object>> videos=template.queryForList(sql);
        //2、转换结果并返回
        for(Map video:videos){
            System.out.println(video);
            System.out.println(video.getClass());
        }
    }

    @org.junit.Test
    public void test2() throws IOException {
        System.out.println(CommonUtils.uuid());
        System.out.println(CommonUtils.uuid());
    }

    @org.junit.Test
    public void test3() throws IOException {
        String imgPath = "E:\\uploadFileTest\\h123.jpg";
        BufferedImage image = ImageIO.read(new FileInputStream(imgPath));
        System.out.println(image);
    }




    @org.junit.Test
    public void test4(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getPool());
        String sql="UPDATE lvbao_perinfor SET infor_headurl=? WHERE user_id=?";
        template.update(sql,"686259A9546F4B7B92CF79AD280587CF", "321");
    }

    @org.junit.Test
    public void test5(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getPool());
        String sql="SELECT t.article_id,t.user_id,a.article_title,a.article_brief,DATE(t.skim_time) as skim_day,t.skim_time FROM lvbao_article AS a,lvbao_trace AS t WHERE t.user_id='09828FA672CB4E7EBDBC30A18FFC9014' AND a.article_id=t.article_id ORDER BY skim_time DESC " +
                "LIMIT 15";
            List<Map<String,Object>> traceMaps=template.queryForList(sql);
            List<TraceBean> traces = new ArrayList<TraceBean>();
            //map转化为实体类
            for(Map map:traceMaps) {
                TraceBean traceBean = mapToTrace(map);
                traces.add(traceBean);
            }

            List<DailyRecordBean<TraceBean>> list = new ArrayList<>();
            int i=0;
            while(i<traces.size()){
                Date day=traces.get(i).getSkimDay();
                DailyRecordBean<TraceBean> dailyRecordBean = new DailyRecordBean<>();
                dailyRecordBean.setRecords(new ArrayList<>());
                int j=i;
                while (j<traces.size()&&traces.get(j).getSkimDay().equals(day)){
                    dailyRecordBean.getRecords().add(traces.get(j++));
                }
                i=j;
                dailyRecordBean.setDate(day);
                list.add(dailyRecordBean);
            }
        for (DailyRecordBean d:list){
            System.out.println(d.getDate()+":");
            System.out.println(d.getRecords());
        }
    }

        private TraceBean mapToTrace(Map map){
            TraceBean trace = new TraceBean();
            trace.setArticleID((String)map.get("article_id"));
            trace.setArticleTitle((String)map.get("article_title"));
            trace.setBrief((String) map.get("article_brief"));
            trace.setSkimTime((Timestamp) map.get("skim_time"));
            trace.setUserID((String)map.get("user_id"));
            trace.setSkimDay((Date)map.get("skim_day"));
            return trace;
        }

        @org.junit.Test
        public void test6(){

        }

        @org.junit.Test
        public void test7(){

        }
}
