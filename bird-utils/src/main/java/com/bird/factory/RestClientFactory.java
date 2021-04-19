package com.bird.factory;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

/**
 * @Author lipu
 * @Date 2021/4/19 14:27
 * @Description
 */
public class RestClientFactory extends HttpComponentsClientHttpRequestFactory {

    @Override
    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {

        if (httpMethod==HttpMethod.GET){
            return new HttpGetRequestWithEntity(uri);
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
