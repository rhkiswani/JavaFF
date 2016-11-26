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
 *
 */
public interface HttpClient {

    String postJson(String url, String json, Map<String, String> headers) throws HttpClientException;

    String putJson(String url, String json, Map<String, String> headers) throws HttpClientException;

    String post(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    String put(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    String get(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    Map<String, String> head(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    String delete(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    Map<String, String> options(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    String patch(String url, Map<String, String> params, Map<String, String> headers) throws HttpClientException;

    String patchJson(String url, String json, Map<String, String> headers) throws HttpClientException;
}
