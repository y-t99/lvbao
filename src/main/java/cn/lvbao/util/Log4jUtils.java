package cn.lvbao.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author yuanyuan
 * #create 2019-10-06-20:42
 */
public class Log4jUtils {
    private static Logger LOGGER;
    static {
        LOGGER=Logger.getLogger("cn.lvbao.logger");//获得日志纪录器
        InputStream inputStream = Log4jUtils.class.getClassLoader().getResourceAsStream("log4j.properties");
        Properties properties=new Properties();
        try {
            properties.load(inputStream);
            PropertyConfigurator.configure(properties);//加载配置信息
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("日志配置文件加载失败");
        }
    }
    public static Logger getLogger(){
        return LOGGER;
    }
}
