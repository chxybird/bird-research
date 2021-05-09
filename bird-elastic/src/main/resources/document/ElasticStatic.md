# ELK调研

### 一、Elasticsearch

##### 1.RestFulAPI

创建索引库(使用默认参数创建)

```
URL PUT http://IP:9200/索引库名称

返回的结果
{
    "acknowledged": true,//是否创建成功
    "shards_acknowledged": true,//副本是否也创建成功
    "index": "test-index"//索引名称
}
```

创建索引库(设置具体的参数)

```
URL PUT http://IP:9200/索引库名称
```

查看索引库详细信息

```
URL GET http://IP:9200/索引库名称

返回的结果
{
    "test-index": {
        "aliases": {},
        "mappings": {},
        "settings": {
            "index": {
                "creation_date": "1620566559880",
                "number_of_shards": "1",//分片数
                "number_of_replicas": "1",//副本数 备份
                "uuid": "MFzURfm1QimJPVkXYhGlng",
                "version": {
                    "created": "7090299"
                },
                "provided_name": "test-index"
            }
        }
    }
}
```

