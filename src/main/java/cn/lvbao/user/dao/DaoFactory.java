package cn.lvbao.user.dao;

import cn.lvbao.user.dao.impl.UserDaoImpl;
import cn.lvbao.user.domain.User;
import cn.lvbao.util.CglibUtil;
import cn.lvbao.util.EncryptUtil;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yuanyuan
 * #create 2019-10-24-8:24
 */
public class DaoFactory {

    private static UserDao USER_DAO;
    static{
        /*
         * 对UserDao进行加强
         */
        USER_DAO=(UserDao)CglibUtil.getEnhanceObject(UserDaoImpl.class,new MethodInterceptor(){
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                String methonName=method.getName();

                /*
                 *  如果为添加新用户,或者更新方法,对密码进行加密
                 */
                if("addUser".equals(methonName) || "updatePass".equals(methonName)){
                    //1、获取密码
                    String pass = null;
                    if("addUser".equals(methonName)){
                        pass=((User)objects[0]).getLoginpass();
                    }
                    if ("updatePass".equals(methonName)){
                        pass=(String)objects[1];
                    }
                    //2、进行加密
                    pass= EncryptUtil.encrypt(pass);
                    //3、加密后密码覆盖原来的
                    if("addUser".equals(methonName)){
                        ((User)objects[0]).setLoginpass(pass);
                    }
                    if ("updatePass".equals(methonName)){
                        objects[1]=pass;
                    }
                }
                /*
                 * 执行方法
                 */
                Object  returnResult= methodProxy.invokeSuper(o, objects);

                /*
                 * 需要密码,对数据库密码进行解码
                 */
                if ("queryByCode".equals(methonName) || "queryByEmail".equals(methonName)
                        ||"queryByName".equals(methonName)){
                    User user= (User) returnResult;
                    String pass=user.getLoginpass();
                    if(pass!=null){
                        //1、解密
                        pass=EncryptUtil.decrypt(pass);
                    }
                    //2、将解密后的数据替代原来的
                    user.setLoginpass(pass);
                }
                return returnResult;
            }
        });
    }
    public static UserDao getUserDao(){
        return USER_DAO;
    }
}
