package cn.lvbao.user.dao.impl;

import cn.lvbao.user.dao.UserDao;
import cn.lvbao.user.domain.User;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

/**
 *@Description:用户模块持久层实现，操作数据库
 *@Author: ms
 *@Date: 2019/10/15 14:25
 */
public class UserDaoImpl implements UserDao {
    /**
     * 缺省,只有同个包下的类才能使用
     */
    UserDaoImpl(){

    }

    /**
     * 创建由于简化数据库操作代码的JdbcTemplate对象（需获取连接池）
     */
    private String tabName="lvbao_user";
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());

    /**
     * 添加用户
     * @param user
     */
    @Override
    public boolean addUser(User user){
        //在用户表中插入传入的用户对象
        String sql=" INSERT INTO "+tabName+" ( id, loginname, loginpass, email, status, activationCode ) " +
                " VALUES " +
                "( ?, ?, ?, ?, ?, ? )";
        Object[] params={user.getId(),user.getLoginname(),user.getLoginpass(),
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
        String sql="SELECT * FROM "+tabName+" WHERE loginname=?";
        try{
            Map<String,Object> userMap= template.queryForMap(sql, name);
            return mapToBean(userMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据邮箱查找用户并返回该条记录
     * @param email
     * @return
     */
    @Override
    public User queryByEmail(String email){
        User user=null;
        String sql="SELECT * FROM "+tabName+" WHERE email=?";
        try{
            Map<String,Object> userMap= template.queryForMap(sql, email);
            user = mapToBean(userMap);
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 用户修改密码
     * @param id
     * @return
     */
    @Override
    public boolean updatePass(String id,String pass) {
        String sql="UPDATE "+tabName+" SET loginpass=? WHERE id=?";
        try{
            template.update(sql, pass,id);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isExist(String name) {
        String sql="SELECT COUNT(*) FROM "+tabName+" WHERE loginname=?";
        try {
            int count=template.queryForObject(sql, int.class,name);
            return count==1;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 根据激活码查找用户并返回该条记录
     * @param code
     * @return
     */
    @Override
    public User queryByCode(String code){
        User user ;
        String sql="SELECT * FROM "+tabName+" WHERE activationCode=?";
        try{
            Map<String,Object> userMap= template.queryForMap(sql, code);
            return mapToBean(userMap);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    @Override
    public boolean updateStatus(String id, int status){
        String sql="UPDATE "+tabName+" SET status=? WHERE id=?";
        try{
            template.update(sql, status, id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 把查询数据库的map结果封装为user对象
     * @param userMap
     * @return
     */
    private User mapToBean(Map<String,Object> userMap){
        User user=new User();
        user.setId((String) userMap.get("id"));
        user.setLoginname((String)userMap.get("loginname"));
        user.setLoginpass((String)userMap.get("loginpass"));
        user.setStatus((boolean)userMap.get("status"));
        user.setEmail((String)userMap.get("email"));
        user.setActivationCode((String)userMap.get("activationCode"));
        return user;
    }

}

