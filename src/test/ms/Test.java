package ms;

import cn.lvbao.domain.Result;
import cn.lvbao.idea.dao.IdeaDao;
import cn.lvbao.idea.dao.impl.IdeaDaoImpl;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;
import cn.lvbao.idea.service.IdeaService;
import cn.lvbao.idea.service.impl.IdeaServiceImpl;
import cn.lvbao.user.dao.DaoFactory;
import cn.lvbao.user.dao.UserDao;
import cn.lvbao.user.domain.User;
import cn.lvbao.util.CommonUtils;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.http.HttpSession;
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
    public void test2(){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());

        List<VideoBean> videoList = new ArrayList<VideoBean>();
        //1、查询
        String sql="SELECT * FROM lvbao_video";
        List<Map<String,Object>> videos=template.queryForList(sql);
        //2、将查询所得map列表转化为bean列表
        for(Map<String,Object> map:videos){
            VideoBean videoBean = new VideoBean();
            videoBean.setVideoId((String)map.get("video_id"));
            videoBean.setVideoUrl((String)map.get("video_url"));
            videoList.add(videoBean);
        }
        //3、返回结果
        System.out.println(videoList);
    }

    @org.junit.Test
    public void test3(){
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());

        List<VideoBean> videoList = new ArrayList<VideoBean>();
        //1、查询
        String sql="SELECT * FROM lvbao_video";
        List<Map<String,Object>> videos=template.queryForList(sql);
        //2、将查询所得map列表转化为bean列表
        for(Map<String,Object> map:videos){
            VideoBean videoBean = new VideoBean();
            videoBean.setVideoId((String)map.get("video_id"));
            videoBean.setVideoUrl((String)map.get("video_url"));
            videoList.add(videoBean);
        }
        System.out.println(videoList);
        //3、返回结果
    }
}
