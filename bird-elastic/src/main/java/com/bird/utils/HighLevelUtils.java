package com.bird.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @Author lipu
 * @Date 2021/5/8 14:51
 * @Description ES高级客户端工具类
 */
@Slf4j
public class HighLevelUtils {
    /**
     * ES地址 集群配置多个即可
     */
    private static final String ES_HOSTNAME = "192.168.198.142";
    /**
     * 连接ES的端口 ES 7.X版本放弃transport client的9300 使用http的9200端口
     */
    private static final Integer ES_PORT = 9200;
    /**
     * 连接ES的方式 使用HTTP的方式
     */
    private static final String ES_SCHEME = "http";
    /**
     * 分片数 一个所以可以分成多片,并存在ES集群上,单机下一个所以的分片都在一台机器上
     */
    private static final String SHARDS = "index.number_of_shards";
    /**
     * 副本数 副本数就是备份数,如果一个索引有2个分片,那么每个分片都有一个完全一样的备份分片
     */
    private static final String REPLICAS = "index.number_of_replicas";
    private static final RestHighLevelClient client;

    /**
     * @Author lipu
     * @Date 2021/5/8 14:58
     * @Description 初始化连接信息
     */
    static {
        HttpHost httpHost = new HttpHost(ES_HOSTNAME, ES_PORT, ES_SCHEME);
        //集群环境下配置多个HttpHost然后交给RestClientBuilder
        RestClientBuilder builder = RestClient.builder(httpHost);
        client = new RestHighLevelClient(builder);

    }

    /**
     * @Author lipu
     * @Date 2021/5/8 15:02
     * @Description 创建索引库
     */
    public static void initIndex(String indexName) {
        if (indexName == null) {
            log.info("索引名称为空,创建索引失败");
            return;
        }
        try {
            //构建请求
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            //创建默认Settings
            request.settings(Settings.builder()
                    .put(SHARDS, 5)
                    .put(REPLICAS, 1)
            );
            //创建默认mappings 相当于定义表结构 TODO
            //发送请求 创建索引
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("索引创建失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/8 15:57
     * @Description 判断索引库是否存在
     */
    public static boolean exist(String indexName) {
        if (indexName == null) {
            log.info("索引名称为空,无法判断");
            return false;
        }
        try {
            //构建请求
            GetIndexRequest request = new GetIndexRequest(indexName);
            //发送请求
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("索引库判断异常");
            return false;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/8 16:13
     * @Description 查询索引库信息
     */
    public static String getIndex(String indexName) {
        if (indexName == null) {
            log.info("索引名称为空,无法获取索引信息");
            return null;
        }
        try {
            GetIndexRequest request = new GetIndexRequest(indexName);
            GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
            Map<String, MappingMetadata> mappings = response.getMappings();
            Map<String, Settings> settings = response.getSettings();
            Map<String, List<AliasMetadata>> aliases = response.getAliases();
            //TODO 数据处理
            return null;
        } catch (Exception e) {
            log.info("索引库信息查询失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/8 16:14
     * @Description 删除索引库
     */
    public static void deleteIndex(String indexName) {
        if (indexName == null) {
            log.info("索引名称为空,删除失败");
            return;
        }
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            client.indices().delete(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.info("索引库删除失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/8 17:07
     * @Description 添加文档
     */
    public void addDocument(String json, String indexName) {
        if (json == null || indexName == null) {
            log.info("文档数据或索引库名称不能为空");
            return;
        }
        try {
            //指定索引库
            IndexRequest request = new IndexRequest();
            request.index(indexName);
            //设置数据 指定类型
            request.source(json, XContentType.JSON);
            //添加文档
            client.index(request, RequestOptions.DEFAULT);
            client.close();
        } catch (Exception e) {
            log.info("文档添加失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2021/5/9 1:05
     * @Description 批量添加文档
     */
    public void addDocumentBatch(String json, String indexName) {
        if (json == null || indexName == null) {
            log.info("文档数据或索引库名称不能为空");
            return;
        }
    }


}
