package cn.lvbao.index.service;

import cn.lvbao.index.domain.Result;

/**
 * @author yuanyuan
 * #create 2019-09-24-19:42
 */
public interface SearchService {

    /**
     * 查找垃圾信息
     * @param keyword
     * @return
     */
    Result getKeyMsg(String keyword);

    /**
     * 查找提示信息
     * @param word
     * @return
     */
    Result getPromptWord(String word);
}
