package cn.lvbao.index.DataImport;

import cn.lvbao.util.JDBCUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-10-01-8:52
 */
public class ESDataImport {


    public static void main(String[] args) throws IOException {

        //1、获取高级es客户端
        // 指定集群
        Settings settings=Settings.builder().put("cluster.name","my-application").build();
        // 创建访问es服务器的客户端
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http")
                )
        );
        //2、获取数据库keywords
        //查找数据库记录条,封装为列表
        JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());//获取sql执行工具
        String sql="SELECT keyword FROM keyhouse ";
        List<String> keywords = template.queryForList(sql,String.class);

        //3、进行批量插入
        BulkRequest request;
        String index="fkeywords";//es索引
        String field="keyword";//字段
        int i=0;
        while(i<keywords.size()){
            request= new BulkRequest();
            for(int j=0;j<10 && i<keywords.size();j++) {//每次请求添加十条记录
                request.add(new IndexRequest(index)
                        .source(XContentType.JSON, field, keywords.get(i)));
                i++;
            }
            client.bulk(request, RequestOptions.DEFAULT);
        }
        client.close();
    }
}
