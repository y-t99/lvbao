package cn.lvbao.user.dao;

import cn.lvbao.user.domain.User;

/**
 * @Description: 用户模块dao层接口
 * @Author: ms
 * @Date: 2019/10/11 14:10
 */
public interface UserDao {
    /**
     * 添加用户
     * @param user
     * @return
     */
    boolean addUser(User user);


    /**
     * 更新用户状态
     * @return
     */
    boolean updateStatus(String id,int status);

    /**
     * 按名字查找用户
     * @param name
     * @return
     */
    User queryByName(String name);

    /**
     * 按激活码查找用户
     * @param code
     * @return
     */
    User queryByCode(String code);

    /**
     * 按邮箱地址查找用户
     * @param email
     * @return
     */
    User queryByEmail(String email);


}

