package cn.lvbao.index.domain;

import cn.lvbao.index.code.ResultEnum;

import java.util.List;

/**
 * 响应实体
 * 返回给前端的结果,此类的
 *  属性=值
 * 会包装成json返回给前端
 */
public class Result{
    /**
     * 状态码
     */
    private int code;
    /**
     * 名字
     */
    private String name;
    /**
     * 分类
     */
    private String category;
    /**
     * 父类
     */
    private String parent;
    /**
     * 子类们
     */
    private List<String> childrens;
    /**
     * 查找到的信息
     */
    private String info;



    public Result(){

    }
    public Result(ResultEnum resultEnum){
        this.code=resultEnum.getCode();
    }

    public Result(ResultEnum resultEnum,String parent,List<String> childrens,String info){
        this(resultEnum);
        this.parent=parent;
        this.childrens=childrens;
        this.info=info;
    }
    public Result(ResultEnum resultEnum,Garbage garbage){
        this(resultEnum);
        this.name=garbage.getName();
        this.category=garbage.getCategory();
        this.parent=garbage.getParent().getName();
        this.childrens=null;
        this.info=garbage.getInfo();
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<String> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<String> childrens) {
        this.childrens = childrens;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", parent='" + parent + '\'' +
                ", childrens=" + childrens +
                ", info='" + info + '\'' +
                '}';
    }
}



