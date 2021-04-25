package com.bird.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.HttpMethod;


/**
 * @Author lipu
 * @Date 2021/4/18 20:36
 * @Description Rest工具类
 */
@Slf4j
public class RestUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        //设置restTemplate字符集为UTF-8
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        //设置restTemplate支持发送GET请求设置body数据
        restTemplate.setRequestFactory(new RestClientFactory());
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 10:38
     * @Description url参数拼接
     */
    private static String paramInstall(String url, Map<String, Object> param) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        //参数拼接
        if (param!=null){
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.build().toString();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 11:32
     * @Description path参数拼接
     */
    private static String pathInstall(String url, Map<String, Object> pathParam) {
        StringBuilder pathUrl = new StringBuilder(url);
        if (pathParam!=null){
            for (Map.Entry<String, Object> entry : pathParam.entrySet()) {
                pathUrl.append("/{").append(entry.getKey()).append("}");
            }
        }
        return pathUrl.toString();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 8:57
     * @Description GET 参数拼接 转JSON
     */
    public static String getByParam(String url, Map<String, Object> param) {
        String finalUrl = param == null ? url : paramInstall(url, param);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(finalUrl, HttpMethod.GET, null, String.class);
        return responseEntity.getBody();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 10:46
     * @Description GET path拼接 转JSON
     */
    public static String getByPath(String url, Map<String, Object> pathVariable) {
        //path参数封装
        String pathUrl = pathInstall(url, pathVariable);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(pathUrl, HttpMethod.GET, null, String.class, pathVariable);
        return responseEntity.getBody();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 10:48
     * @Description GET 请求体JSON 转JSON
     */
    public static String getByBody(String url,Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity=new HttpEntity<>(body,headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(url, HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 15:17
     * @Description POST 请求体 转JSON
     */
    public static String postByBody(String url,Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity=new HttpEntity<>(body,headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(url, HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    /**
     * @Author lipu
     * @Date 2021/4/19 14:39
     * @Description 通用请求 返回json
     */
    public static String rest(String url,HttpMethod method,Map<String,Object> param,
                              Map<String,Object> pathVariable,Object body,HttpHeaders headers){
        if (url==null){
            return null;
        }
        //path参数封装
        String pathUrl = pathInstall(url, pathVariable);
        //param参数组装
        String finalUrl = param == null ? pathUrl : paramInstall(pathUrl, param);
        //header参数封装
        if (headers==null){
            headers=new HttpHeaders();
        }
        //body参数封装
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity=new HttpEntity<>(body,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(finalUrl, method, entity, String.class);
        return responseEntity.getBody();
    }

    static class RestClientFactory extends HttpComponentsClientHttpRequestFactory {
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {

            if (httpMethod==HttpMethod.GET){
                return new RestClientFactory.HttpGetRequestWithEntity(uri);
            }
            return super.createHttpUriRequest(httpMethod, uri);
        }


        /**
         * @Author lipu
         * @Date 2021/4/19 14:30
         * @Description 支持GET发送body
         */
        private static final class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {
            public HttpGetRequestWithEntity(final URI uri) {
                super.setURI(uri);
            }
            @Override
            public String getMethod() {
                return HttpMethod.GET.name();
            }
        }
    }
}
