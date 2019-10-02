package cn.lvbao.index.dao;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-01-10:49
 */
public interface ESDao {
    List<String> findPromptWord(String word) throws IOException;
}
