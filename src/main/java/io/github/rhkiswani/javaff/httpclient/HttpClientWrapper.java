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

import java.util.Map;

/**
 * @author Mohamed Kiswani
 * @since 0.0.20
 * @see io.github.rhkiswani.javaff.httpclient.HttpClient
 */
public class HttpClientWrapper implements HttpClient{
    private HttpClient httpClient;

    public HttpClientWrapper(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public String postJson(String url, String json, Map<String, String> headers) throws HttpClientException {
        return httpClient.postJson(url, json, headers);
    }

    @Override
    public String putJson(String url, String json, Map<String, String> headers) throws HttpClientException {
        return httpClient.putJson(url, json, headers);
    }

    @Override
    public String post(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.post(url, params, headers);
    }

    @Override
    public String put(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.put(url, params, headers);
    }

    @Override
    public String get(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.get(url, params, headers);
    }

    @Override
    public Map<String, String> head(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.head(url, params, headers);
    }

    @Override
    public String delete(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.delete(url, params, headers);
    }

    @Override
    public Map<String, String> options(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.options(url, params, headers);
    }

    @Override
    public String patch(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException {
        return httpClient.patch(url, params, headers);
    }

    @Override
    public String patchJson(String url, String json, Map<String, String> headers) throws HttpClientException {
        return httpClient.patchJson(url, json, headers);
    }
}
