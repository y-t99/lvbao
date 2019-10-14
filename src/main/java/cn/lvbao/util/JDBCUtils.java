package cn.lvbao.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author lvbao
 * #create 2019-09-22-19:18
 */
public class JDBCUtils{
    private static DataSource pool;

    static{
        initDataSource();
    }

    /**
     * 获取连接池
     * @return
     */
    public static DataSource getPool(){
        return pool;
    }
    /**
     * 读取配置文件，初始化连接池
     */
    private static void initDataSource() {
        Properties properties=new Properties();
        InputStream in=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Druid链接池配置文件加载失败！！！");
        }
        try {
            pool= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("连接池创建失败");
        }
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection(){
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭连接
     * @param connection
     */
    public void close(Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
