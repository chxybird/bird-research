package com.bird.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author lipu
 * @Date 2021/4/15 9:11
 * @Description Json工具类
 */
@Slf4j
@Component
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String JSON_SUFFIX = ".json";

    static {

        //json序列化时候如果字段为空则不序列化该字段
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
        //JSON反序列化为对象的时候如果对象没有对应字段就忽略该字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //JSON序列化时如果对象为空则不抛出异常 经调研好像不设置也不会报错
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //JSON序列化美化输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //自定义序列化反序列化日期格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    /**
     * @Author lipu
     * @Date 2021/4/15 9:18
     * @Description 实体转JSON
     */
    public static String entityToJson(Object entity) {
        if (entity == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (Exception e) {
            log.error("对象转JSON失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/15 10:30
     * @Description JSON转对象
     */
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (json == null || clazz == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("JSON转对象失败");
            return null;
        }
    }


    /**
     * @Author lipu
     * @Date 2021/4/15 11:28
     * @Description JSON转List集合
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (Exception e) {
            log.error("JSON转LIST失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/15 13:34
     * @Description JSON转Set集合
     */
    public static <T> Set<T> jsonToSet(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, new TypeReference<Set<T>>() {
            });
        } catch (Exception e) {
            log.error("JSON转SET失败");
            return null;
        }
    }

    /**
     * @return
     * @Author lipu
     * @Date 2021/4/15 11:28
     * @Description JSON转MAP
     */
    public static Map<String, Object> jsonToMap(String json) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("JSON转MAP失败");
            return null;
        }
    }

    /**
     * @Author lipu
     * @Date 2021/4/15 15:24
     * @Description 实体转JSON文件
     */
    public static void entityToFile(String path,String fileName, Object entity) {
        try {
            objectMapper.writeValue(new File(path,fileName+JSON_SUFFIX), entity);
        } catch (IOException e) {
            log.error("实体转JSON文件发生异常");
        }
    }

}
