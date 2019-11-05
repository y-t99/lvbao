package cn.lvbao.user.util;


import cn.lvbao.user.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 小工具集合类
 * @Author: ms
 * @Date: 2019/10/4 0:35
 */
public class CommonUtils {
    /**
     * 返回一个不重复的随机字符串，可作为id和激活码
     * @return
     */
    public static String getRandomString(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }

    /**
     * 把Map集合转换成实体类对象
     * @param map
     * @param ofClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(Map<String,Object> map,Class<T> ofClass){
        try {
            T bean = ofClass.newInstance();
            BeanUtils.populate(bean, map);
            return bean;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            System.out.println(getRandomString());
        }
    }
}

