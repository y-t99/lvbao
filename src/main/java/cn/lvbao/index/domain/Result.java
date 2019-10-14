package cn.lvbao.index.domain;

import cn.lvbao.code.ResultEnum;

import java.util.ArrayList;
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
    /**
     * 提示词数量
     */
    private int promptNum;

    /**
     * 查找单词
     */
    private List<String> words;

    /**
     * 文章简介分页
     */
    private PageBean<ArticleBrief> pageBean;
    public Result(){

    }
//--------------------------------搜索词查找失败封装开始-------------------------------------//
    public Result(ResultEnum resultEnum){
        this.code=resultEnum.getCode();
    }
//--------------------------------搜索词查找失败封装结束-------------------------------------//
//--------------------------------提示词查找封装开始-------------------------------------//
    public Result(ResultEnum resultEnum,int num,List<String> words){
        this.code=resultEnum.getCode();
        promptNum=num;
        this.words=words;
    }
//--------------------------------提示词查找封装结束-------------------------------------//
//--------------------------------搜索词查找成功封装开始-------------------------------------//
    public Result(ResultEnum resultEnum,Garbage garbage){
        this(resultEnum);
        this.name=garbage.getName();
        this.category=garbage.getCategory();
        this.parent=garbage.getParent().getName();
        this.childrens=getStrList(garbage.getChildrens());
        this.info=garbage.getInfo();
    }

    private List<String> getStrList(List<Garbage> childrens) {
        if(childrens==null){
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        for(Garbage children:childrens){
            list.add(children.getName());
        }
        return list;
    }
//--------------------------------搜索词查找成功封装结束-------------------------------------//
//-------------------------------文章简介分页成功封装开始-------------------------------------//
    public Result(ResultEnum resultEnum,PageBean<ArticleBrief> pageBean){
        this.code=resultEnum.getCode();
        this.pageBean=pageBean;
    }
//--------------------------------文章简介分页失败封装开始-------------------------------------//
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

    public int getPromptNum() {
        return promptNum;
    }

    public void setPromptNum(int promptNum) {
        this.promptNum = promptNum;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public PageBean<ArticleBrief> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<ArticleBrief> pageBean) {
        this.pageBean = pageBean;
    }

    @Override
    public String toString() {
        return "Result{" +
                "ienum=" + code +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", parent='" + parent + '\'' +
                ", childrens=" + childrens +
                ", info='" + info + '\'' +
                ", promptNum=" + promptNum +
                ", words=" + words +
                ", pageBean=" + pageBean +
                '}';
    }
}



