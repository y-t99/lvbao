package cn.lvbao.util;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author yuanyuan
 * #create 2019-10-24-8:50
 */
public class CglibUtil {
    public static Object getEnhanceObject(Class<?> clazz,MethodInterceptor interceptor){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        return enhancer.create();
    }
}
