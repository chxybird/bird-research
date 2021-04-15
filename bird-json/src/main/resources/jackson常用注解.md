### 常用类注解

##### @JsonIgnoreProperties

用于忽略对象被序列化时的属性

##### @JsonIgnoreType

当对象是另外一个对象的成员变量时，忽略序列化。

##### @JsonAutoDetect

用于指定序列化反序列化的范围

| 属性值               | 作用                |
| -------------------- | ------------------- |
| ANY                  | 所有权限            |
| NON_PRIVATE          | private之外的       |
| PROTECTED_AND_PUBLIC | protected和public的 |
| PUBLIC_ONLY          | public              |

##### @JsonInclude:

该注解用于控制某些字段不被序列化

| 属性值      | 作用                                        |
| ----------- | ------------------------------------------- |
| ALWAYS      | 默认策略,任何情况都执行序列化               |
| NON_NULL    | 非空字段不被序列化                          |
| NON_DEFAULT | 如果字段是默认值，就不会被序列化            |
| NON_EMPTY   | 非空字段以及空串、size为0的集合都不被序列化 |

### 常用属性注解

##### @JsonProperty

用于指定对象的属性被序列化时的key的值

##### @JsonIgnore

用于忽略对象被序列化时的属性

### SpringBoot整合jackson

```yml
#jackson配置
spring:
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss #日期格式化
    # 序列化相关
    serialization:
      indent_output: true #化输出
    deserialization:
      fail_on_unknown_properties: false #反序列化时对象没有json对应的属性忽略
    defaultPropertyInclusion: NON_EMPTY #如何序列化
    parser:
      allow_unquoted_control_chars: true #允许特殊和转义符
      allow_single_quotes: true #允许单引号
```

