package cn.lvbao.index.dao.impl;

import cn.lvbao.index.dao.ESDao;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-01-10:50
 * 用es查找提示词
 */
public class ESDaoImpl implements ESDao{
    private static ESDaoImpl ES_DAO;
    static {
        ES_DAO=new ESDaoImpl();
    }
    private ESDaoImpl(){

    }
    public static ESDaoImpl getInstance(){
        return ES_DAO;
    }
    @Override
    public List<String> findPromptWord(String word) throws IOException {
        //1、创建访问es服务器的客户端
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http")
                )
        );

        //2、查找得到对象
        SearchRequest searchRequest=new SearchRequest();
        //搜索条件
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("keyword",word))
                .size(10);
        //请求restful
        searchRequest.source(searchSourceBuilder);
        searchRequest.indices("keywords");
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        //搜索结果
        SearchResponse searchResponse=client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();

        //3、将结果封装成列表
        List<String> list=new ArrayList<>();
        for(SearchHit hit:hits){
            String keyword= (String) hit.getSourceAsMap().get("keyword");
            list.add(keyword);
        }
        client.close();
        return list;
    }
}
