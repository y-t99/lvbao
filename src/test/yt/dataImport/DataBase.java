package yt.dataImport;

import org.junit.Test;

import java.io.*;

/**
 * @author yuanyuan
 * #create 2019-09-30-21:55
 */
public class DataBase {
    /**
     *      流读取csv文件
     *      一行一行读取
     *      将每行按","分隔成一个字符串数组
     *      写入数据库
     */
    @Test
    public void writeData() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader(new File("d://product.csv")));
        reader.readLine();
        String line=null;
        int num=0;
        while((line=reader.readLine())!=null){
            String[] strings = line.split(",");
            String keywords=strings[1];
            String catagory;
            switch (strings[3]){
                case "1":
                    catagory="可回收垃圾";
                    break;
                case "2":
                    catagory="有害垃圾";
                    break;
                case "3":
                    catagory="湿垃圾";
                    break;
                case "4":
                    catagory="干垃圾";
                    break;
                default:
                    catagory=null;
            }
        }
    }
}
