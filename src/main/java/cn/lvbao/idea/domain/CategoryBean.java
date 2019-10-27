package cn.lvbao.idea.domain;

import java.util.List;

/**
 *@Description: 目录（分类栏）实体类
 *@Author: ms
 *@Date: 2019/10/26 20:55
 */
public class CategoryBean {
    /**
     * id  分类项的id
     * name  分类项的名称
     * parentId  分类项的上级（父类）的id
     * children  分类项的下级（子类）的id
     */
    private String id;
    private String name;
    private String parentId;
    private List<CategoryBean> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<CategoryBean> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryBean> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CategoryBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                '}';
    }
}

