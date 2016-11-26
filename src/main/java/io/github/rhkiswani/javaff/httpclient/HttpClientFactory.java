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

import io.github.rhkiswani.javaff.detector.ApiDetectorUtil;
import io.github.rhkiswani.javaff.factory.AbstractFactory;

/**
 * @author Mohamed Kiswani
 * @since 0.0.20
 * @see AbstractFactory
 */
public class HttpClientFactory extends AbstractFactory<HttpClient>{

    private static HttpClientFactory instance = new HttpClientFactory();

    private HttpClientFactory(){
        if (ApiDetectorUtil.isApacheHttpClientAvailable()){
            add(Object.class, new ApacheHttpClient());
        }
    }

    public static HttpClientFactory instance(){
        return instance;
    }

    @Override
    protected String getDefaultImplementationUrl() {
        return "https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient";
    }

    public static HttpClient getHttpClient(Class aClass) {
        return new HttpClientWrapper(instance.create(aClass));
    }

}
