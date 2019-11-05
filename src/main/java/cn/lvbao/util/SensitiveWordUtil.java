package cn.lvbao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yuanyuan
 * #create 2019-10-22-10:51
 */
public class SensitiveWordUtil {
    /*
     * 需要对象
     *     1、敏感词树集：便于敏感词检索;sensitiveWordMap
     *     2、当前敏感词：遍历,将其加入到敏感词树集中;key
     *     3、当前树：可能从新建开始,也可能从已有树集中获取。当前敏感词的保存地点;nowMap
     *     4、新map：单敏感次遍历到没有结点对应时,添加新节点,并新map记录敏感次是否结束;newFlagMap
     *     5、下一个map;wordMap：得到结点
     */
    private static Map sensitiveWord;

    /**
     * 初始化敏感词库
     */
    static {
        Set<String> keywords = new HashSet<>();
        InputStream inputStream = SensitiveWordUtil.class.getClassLoader().getResourceAsStream("敏感词库大全.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        String line;

        try {
            while(((line=reader.readLine())!=null)){
                keywords.add(line);
            }
        } catch (IOException e) {
        }

        addSensitiveWordToHashTree(keywords);
    }
    /**
     * 生成敏感词库
     * @param keyWords
     */
    private static void addSensitiveWordToHashTree(Set<String> keyWords) {
        sensitiveWord = new HashMap((keyWords.size()));
        Map nowTree;
        Map newFlagMap;
        /*遍历keywords
            |--遍历每个单词
                |--判断当前树是否有该字符为根的子树,每次从根开始
                    |--为空,生成新的树,新树保存在当前树下,并把当前树指向新树
                    |--不为空,当前树指向当前字符为根的子树
                |--单词遍历完,当前树的isEnd=1
        */
        for (String currentKey : keyWords) {
            nowTree = sensitiveWord;
            for (int i = 0; i < currentKey.length(); i++) {
                char currentChar = currentKey.charAt(i);
                Object wordMap = nowTree.get(currentChar);
                if (wordMap != null) {
                    nowTree = (Map) wordMap;
                } else {
                    newFlagMap = new HashMap<String, Boolean>();
                    newFlagMap.put("isEnd", false);
                    nowTree.put(currentChar, newFlagMap);
                    nowTree = newFlagMap;
                }
                if (i == currentKey.length() - 1) {
                    nowTree.put("isEnd", true);
                }
            }
        }
    }
    /**
     *查找铭感词位置
     */
    private static int checkSensitiveWord(String text,int beginIndex){
        Map nowTree=sensitiveWord;
        char nowChar;
        Map charTree;
        int length=0;
        boolean isSensitive=false;
        for(int i=beginIndex;i<text.length();i++){
            nowChar=text.charAt(i);
            charTree= (Map) nowTree.get(nowChar);
            if(charTree!=null){
                isSensitive= (boolean) charTree.get("isEnd");
                nowTree=charTree;
                length++;
            }else {
                break;
            }
        }
        if(isSensitive){
            return length;
        }
        return 0;
    }

    /**
     * 替换敏感词
     * @param
     */
    public static String replaceSensitiveWord(String text,String safeWord){
        StringBuilder sb=new StringBuilder(text);
        try {
            for (int i = 0; i < sb.length(); i++) {
                int wordLength = checkSensitiveWord(sb.toString(), i);
                if (wordLength != 0) {
                    sb.replace(i, i + wordLength, safeWord);
                    i += (safeWord.length()-1);
                }
            }
        }catch (IndexOutOfBoundsException e){
            return sb.toString();
        }
        return sb.toString();
    }
}