package ms;

import cn.lvbao.user.dao.DaoFactory;
import cn.lvbao.user.dao.UserDao;
import cn.lvbao.user.dao.impl.UserDaoImpl;
import cn.lvbao.user.domain.User;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

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

    private String tabName="lvbao_user";
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());
    private UserDao userDao= DaoFactory.getUserDao();

    @org.junit.Test
    public void test1(){
        String a="SELECT * FROM lvbao_user WHERE activationCode='7303C31F68DE4635BEFCED37C3D8E0C7'";
        Map<String,Object> userMap= template.queryForMap(a);
        System.out.println(userMap);
        User user=new User();
        user.setId((String) userMap.get("id"));
        user.setLoginname((String)userMap.get("loginname"));
        user.setLoginpass((String)userMap.get("loginpass"));
        user.setEmail((String)userMap.get("email"));
        user.setActivationCode((String)userMap.get("activationCode"));
        System.out.println(user);
    }
    @org.junit.Test
    public void test3(){
//        User user = userDao.queryByName("321");
//        System.out.println(user);
        Long sendTime=Long.parseLong("E45ED6D4#1571476591975".split("#", 2)[1]);

    }

    @org.junit.Test
    public void test2() {
        long t1=System.currentTimeMillis();
        long t2=System.currentTimeMillis()-t1;
        System.out.println(t2);
    }
    @org.junit.Test
    public void test4(){
        long t2=System.currentTimeMillis();
        System.out.println(t2);
    }

}
