package yt.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;


/**
 * @author yuanyuan
 * #create 2019-09-28-14:25
 */
public class EsDemo {
    @Test
    public void test() throws IOException {
        //1、创建访问es服务器的客户端
        RestHighLevelClient client=new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http")
                )
        );

        /*2、查找id是否存在

        GetRequest getRequest = new GetRequest(
                "keywords",
                "_Im9dm0BitEGhDp-spTy");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        if(exists){
            System.out.println("id存在");
        }else{
            System.out.println("id不存在");
        }*/

        //3、条件查询
        SearchRequest searchRequest=new SearchRequest();
        //搜索条件
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("keyword","广告片"))
                            .size(10);
        //请求restful
        searchRequest.source(searchSourceBuilder);
        searchRequest.indices("keywords");
        searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
        //搜索结果
        SearchResponse searchResponse=client.search(searchRequest,RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        System.out.println(hits.getTotalHits().value);
        for(SearchHit hit:hits.getHits()){
            System.out.println();
        }

        //释放资源
        client.close();
    }
}
