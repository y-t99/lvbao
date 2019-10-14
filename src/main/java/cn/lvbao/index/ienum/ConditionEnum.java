package cn.lvbao.index.ienum;

/**
 * @author yuanyuan
 * #create 2019-10-13-17:24
 */
public enum ConditionEnum {
    TIME("time"),
    Start("start");
    private String condition;
    ConditionEnum(String condition){
        this.condition=condition;
    }
}
