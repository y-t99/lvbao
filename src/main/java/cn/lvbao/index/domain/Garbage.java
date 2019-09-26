package cn.lvbao.index.domain;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-09-24-22:18
 * 垃圾实体
 *  name 垃圾名字
 *  category 垃圾所属分类
 *  parent 父类
 *  childrends 子类
 *  info 信息
 */
public class Garbage {
    private String name;
    private String category;
    private Garbage parent;
    private List<Garbage> childrens;
    private String info;

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

    public Garbage getParent() {
        return parent;
    }

    public void setParent(Garbage parent) {
        this.parent = parent;
    }

    public List<Garbage> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Garbage> childrens) {
        this.childrens = childrens;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
