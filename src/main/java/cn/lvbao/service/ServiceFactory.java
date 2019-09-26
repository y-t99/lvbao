package cn.lvbao.service;


import cn.lvbao.service.impl.TestServiceImpl;

/**
 * @author lvbao
 * #create 2019-09-22-20:51
 */
public class ServiceFactory {
    private static TestService TEST_SERVICE;
    static{
        TEST_SERVICE= TestServiceImpl.getInstance();
    }
    public static TestService getTestService(){
        return TEST_SERVICE;
    }
}
