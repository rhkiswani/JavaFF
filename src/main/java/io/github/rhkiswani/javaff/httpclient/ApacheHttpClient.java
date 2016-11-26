/*
 * Copyright 2016 Mohamed Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rhkiswani.javaff.httpclient;

import io.github.rhkiswani.javaff.httpclient.exceptions.HttpClientException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohamed Kiswani
 * @since 0.0.20
 *
 */
class ApacheHttpClient implements HttpClient{

    @Override
    public String postJson(String url, String json, Map<String, String> headers) throws HttpClientException {
        return doJsonRequest(new HttpPost(url), json, headers);
    }

    @Override
    public String putJson(String url, String json, Map<String, String> headers) throws HttpClientException {
        return doJsonRequest(new HttpPut(url), json, headers);
    }

    private String doJsonRequest(HttpRequestBase method, String json, Map<String, String> headers) {
        try {
            CloseableHttpClient c =  HttpClientBuilder.create().build();
            ((HttpEntityEnclosingRequestBase) method).setEntity(new StringEntity(json));
            method.setHeader("Accept", "application/json");
            method.setHeader("Content-type", "application/json");
            setHeaders(method, headers);
            return handleResponse(method, c);
        } catch (Exception e) {
            throw new HttpClientException(e);
        }
    }

    private String handleResponse(HttpRequestBase method, CloseableHttpClient c) throws IOException {
        String response = c.execute(method, new BasicResponseHandler());
        return response;
    }

    @Override
    public String post(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return doRequest(new HttpPost(url), params, headers);
    }

    @Override
    public String put(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return doRequest(new HttpPut(url), params, headers);
    }

    private String doRequest(HttpRequestBase method, Map<String, String> params, Map<String, String> headers) {
        try {
            CloseableHttpClient c = prepareRequest(method, params);
            setHeaders(method, headers);
            return handleResponse(method, c);
        } catch (Exception e) {
            throw new HttpClientException(e);
        }
    }

    private CloseableHttpClient prepareRequest(HttpRequestBase method, Map<String, String> params) throws UnsupportedEncodingException {
        CloseableHttpClient c =  HttpClientBuilder.create().build();
        if(params != null){
            List<NameValuePair> urlParameters = new ArrayList<>();
            for (String key : params.keySet()) {
                urlParameters.add(new BasicNameValuePair(key,  params.get(key)));
            }
            if (method instanceof HttpEntityEnclosingRequestBase){
                ((HttpEntityEnclosingRequestBase) method).setEntity(new UrlEncodedFormEntity(urlParameters));
            }
        }
        return c;
    }

    private void setHeaders(HttpRequestBase method, Map<String, String> headers) {
        if(headers != null){
            for (String header : headers.keySet()) {
                method.setHeader(header, headers.get(header));
            }
        }
    }

    @Override
    public String get(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        if(params != null){
            url += "?";
            for (String key : params.keySet()) {
                url += key + "=" + params.get(key) +"&";
            }
        }
        return doRequest(new HttpGet(url), params, headers);
    }

    @Override
    public Map<String, String> head(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        try {
            HttpHead method = new HttpHead(url);
            CloseableHttpClient client = prepareRequest(method, params);
            setHeaders(method, headers);
            HttpResponse response= client.execute(method);
            Header[] s = response.getAllHeaders();
            Map<String, String> returnedHeaders = new HashMap<>();
            for (Header header : s) {
                returnedHeaders.put(header.getName(), header.getValue());
            }
            return returnedHeaders;
        } catch (Exception e) {
            throw new HttpClientException(e);
        }
    }

    @Override
    public String delete(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return doRequest(new HttpDelete(url), params, headers);
    }

    @Override
    public Map<String, String> options(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return head(url, params, headers);
    }

    @Override
    public String patch(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return doRequest(new HttpPatch(url), params, headers);
    }

    @Override
    public String patchJson(String url, String json, Map<String, String> headers) throws HttpClientException {
        return doJsonRequest(new HttpPatch(url), json, headers);
    }
}
