package cn.lvbao.percenter.domain;

/**
 *@Description: 用户个人信息实体类
 *@Author: ms
 *@Date: 2019/11/5 22:50
 */
public class PerInforBean {
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户id
     */
    private String userID;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private String sex;
    /**
     * 所在地
     */
    private String address;
    /**
     * 头像存放路径
     */
    private String headUrl;
    /**
     * 个性签名
     */
    private String signature;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "PerInforBean{" +
                "userName='" + userName + '\'' +
                ", userID='" + userID + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}

