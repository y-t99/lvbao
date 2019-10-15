package cn.lvbao.user.dao;

import cn.lvbao.user.domain.User;
import cn.lvbao.user.util.CommonUtils;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

/**
 *@Description:用户模块持久层实现
 *@Author: ms
 *@Date: 2019/10/15 14:25
 */
public class UserDaoImpl implements UserDao{
    /**
     * 创建由于简化数据库操作代码的JdbcTemplate对象（需获取连接池）
     */
    private String tabName="user_login";
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());

    /**
     * 添加用户
     * @param user
     */
    @Override
    public boolean addUser(User user){
        //在用户表中插入传入的用户对象
        String sql=" INSERT INTO "+tabName+" ( id, name, password, email, status, activationCode ) " +
                " VALUES " +
                "( ?, ?, ?, ?, ?, ? )";
        Object[] params={user.getId(),user.getName(),user.getPwd(),
                user.getEmail(),user.getStatus(),
                user.getActivationCode()};
        try {
            template.update(sql, params);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户名查找用户信息并返回该条记录
     * @param name
     * @return
     */
    @Override
    public User queryByName(String name){
        User user=null;
        String sql="SELECT * FROM "+tabName+" WHERE name=?";
        try{
            Map<String,Object> userMap= template.queryForMap(sql, name);
            user=CommonUtils.toBean(userMap, User.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据邮箱查找用户并返回该条记录
     * @param email
     * @return
     */
    @Override
    public User queryByEmail(String email){
        User user=null;
        String sql="SELECT COUNT(*) FROM "+tabName+" WHERE email=?";
        try{
            Map<String,Object> userMap= template.queryForMap(sql, email);
            user=CommonUtils.toBean(userMap, User.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据激活码查找用户并返回该条记录
     * @param code
     * @return
     */
    @Override
    public User queryByCode(String code){
        String sql="SELECT * FROM "+tabName+" WHERE activationCode=?";
        Map<String,Object> userMap= template.queryForMap(sql, code);
        User user=CommonUtils.toBean(userMap, User.class);
        return user;
    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    @Override
    public boolean updateStatus(String id, int status){
        String sql="UPDATE t_user SET status=? WHERE uid=?";
        try{
            template.update(sql, status, id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void f( ){
        String sql="";
        try{
            template.update(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

