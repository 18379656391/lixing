package com.lixing.lixingdemo;

import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.pojo.Student;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
class LixingDemoApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    @Resource
    private CuratorFramework curatorFramework;

    /**
     * InterProcessMutex：分布式可重入排它锁
     * InterProcessSemaphoreMutex：分布式排它锁
     * InterProcessReadWriteLock：分布式读写锁
     */
    @Test
    void testZookeeperLock() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, "/testLock");
        try {
            lock.acquire();
            System.out.println("获取zookeeper分布式锁成功---");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.release();
        }
    }

    @Test
    void testCreateIndex() throws IOException {
        //1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("java_index");
        //2.客户端执行请求
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void testQueryIndex() throws IOException {
        //获取索引，判断是否存在
        GetIndexRequest request = new GetIndexRequest("java_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    @Test
    void testDeleteIndex() throws IOException {
        //删除索引
        DeleteIndexRequest request = new DeleteIndexRequest("java_index");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    //文档
    @Test
    void testCreateDoc() throws IOException {
        //创建文档
        //1.创建对象
        Student student = new Student(2021091121L, "张三", 22);
        HashMap<String, Object> inMap = new HashMap<>();
        inMap.put("stuNo", 20210921122L);
        inMap.put("name", "李四");
        inMap.put("age", 24);
        inMap.put("sex", "男");
        //2.创建请求
        IndexRequest request = new IndexRequest("java_index");
        //request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1L));
        //3.将数据放入请求
        //request.source(JSON.toJSONString(student), XContentType.JSON);
        request.source(JSON.toJSONString(inMap), XContentType.JSON);
        //4.发请求
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    void testUpdateDocument() throws IOException {
        //更新文档
        HashMap<String, Object> inMap = new HashMap<>();
        inMap.put("sex", "男");
        inMap.put("age", 23);
        UpdateRequest request = new UpdateRequest();
        request.index("java_index").id("1").doc(inMap);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    void testGetDocument() throws IOException {
        //获取文档
        GetRequest request = new GetRequest("java_index").id("1");
        //不获取返回的_source的上下文
        //request.fetchSourceContext(new FetchSourceContext(false));
        boolean isExist = client.exists(request, RequestOptions.DEFAULT);
        if (isExist) {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            System.out.println(response.getSourceAsString());
            System.out.println(response);
        }
    }

    @Test
    void delDocument() throws IOException {
        //删除文档
        DeleteRequest request = new DeleteRequest("java_index").id("1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void batchInsert() throws IOException {
        //批量插入
        List<Student> students = new ArrayList<>();
        students.add(new Student(2021091121L, "s1", 22));
        students.add(new Student(2021091122L, "s2", 22));
        students.add(new Student(2021091123L, "s3", 22));
        students.add(new Student(2021091124L, "s4", 22));
//        students.add(new Student(2021091125L, "s5", 22));
//        AtomicReference<BulkRequest> bulkRequest = new AtomicReference<>();
//        students.forEach(s -> {
//            bulkRequest.set(new BulkRequest().add(
//                    new IndexRequest().index("java_index").source(JSON.toJSONString(s), XContentType.JSON)));
//        });
        //lambda表达式相当于内部类，外部类传给内部类的参数，并不是用的原来的参数，而是进行浅拷贝，是利用自身的构造器对传入的参数进行备份，所以内部类如果对参数进行了修改，外部类的参数是不受影响的，为了保持参数的一致性，lambda表达式内部使用的
        //参数必须是final定义的
        //使用AtomicReference,使用了CAS乐观锁，取的是最新的值
        //BulkResponse response = client.bulk(bulkRequest.get(), RequestOptions.DEFAULT);
        BulkRequest bulkRequest = new BulkRequest();
        for (Student t : students) {
            bulkRequest.add(new IndexRequest().index("java_index").source(JSON.toJSONString(t), XContentType.JSON));
        }
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(response.hasFailures());
    }

    //查询
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // HighlightBuilder 构建高亮
    // TermQueryBuilder 构建精确查询
    // MatchAllQueryBuilder
    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("java_index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询条件，使用工具类快速实现
        // termQuery 精确匹配
        // matchAllQuery 查询所有
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("name", "s");
        sourceBuilder.query(matchPhrasePrefixQueryBuilder);
        //sourceBuilder.from(1);
        //sourceBuilder.size(10);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }


}
