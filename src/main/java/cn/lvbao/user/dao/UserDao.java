package cn.lvbao.user.dao;

import cn.lvbao.percenter.domain.PerInforBean;
import cn.lvbao.user.domain.User;

/**
 * 用户模块dao层接口
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
    boolean updateStatus(String id, int status);

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

    /**
     * 修改用户密码
     * @param id
     * @param pass
     * @return
     */
    boolean updatePass(String id, String pass);

    /**
     * 根据名字查找用户是否存在
     * @param name
     * @return
     */
    boolean isExist(String name);

    /**
     * 新用户个人中心资料建档，存放默认头像
     * @param user
     */
    void fileUserInfor(User user);
}

