package cn.lvbao.index.DataImport;

import cn.lvbao.util.CommonUtils;

/**
 * @author yuanyuan
 * #create 2019-10-14-10:46
 */
public class ABDataImport {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String uuid = CommonUtils.uuid();
            System.out.println(uuid);
        }
    }
}
