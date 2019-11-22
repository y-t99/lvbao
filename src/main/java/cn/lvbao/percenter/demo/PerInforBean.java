package cn.lvbao.percenter.demo;

/**
 *@Description: 个人资料实体类
 *@Author: ms
 *@Date: 2019/11/5 11:04
 */
public class PerInforBean {
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
                "sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}

