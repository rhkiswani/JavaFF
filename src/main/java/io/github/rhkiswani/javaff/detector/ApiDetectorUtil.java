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
package io.github.rhkiswani.javaff.detector;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class ApiDetectorUtil {

    public static final ApiMetadata JPA_API_METADATA = new ApiMetadata("javax.persistence", "javax.persistence.Id");
    public static final ApiMetadata SLF4_API_METADATA = new ApiMetadata("org.slf4j", "org.slf4j.Logger");
    public static final ApiMetadata JACKSON_API_METADATA = new ApiMetadata("com.fasterxml.jackson.core", "com.fasterxml.jackson.databind.ObjectMapper");
    public static final ApiMetadata APACHE_HTTPCLIENT_API_METADATA = new ApiMetadata("org.apache.httpcomponents", "org.apache.http.NameValuePair");
    public static final ApiMetadata GSON_METADATA = new ApiMetadata("com.google.code.gson", "com.google.gson.Gson");


    private ApiDetectorUtil(){}

    private static Boolean isJPAAvailable = null;
    private static Boolean isSlf4jAvailable = null;
    private static Boolean isJacksonAvailable = null;
    private static Boolean isGsonAvailable = null;
    private static Boolean isApacheHttpClientAvailable = null;

    public static Boolean isJPAAvailable(){
        if(isJPAAvailable == null){
            isJPAAvailable = ApiDetectorFactory.getDetector().isAvailable(JPA_API_METADATA);
        }
        return isJPAAvailable;
    }

    public static Boolean isSlf4jAvailable(){
        if(isSlf4jAvailable == null){
            isSlf4jAvailable = ApiDetectorFactory.getDetector().isAvailable(SLF4_API_METADATA);
        }
        return isSlf4jAvailable;
    }

    public static Boolean isJacksonAvailable(){
        if(isJacksonAvailable == null){
            isJacksonAvailable = ApiDetectorFactory.getDetector().isAvailable(JACKSON_API_METADATA);
        }
        return isJacksonAvailable;
    }

    public static boolean isApacheHttpClientAvailable() {
        if(isApacheHttpClientAvailable == null){
            isApacheHttpClientAvailable = ApiDetectorFactory.getDetector().isAvailable(APACHE_HTTPCLIENT_API_METADATA);
        }
        return isApacheHttpClientAvailable;
    }

    public static boolean isGsonAvailable() {
        if(isGsonAvailable == null){
            isGsonAvailable = ApiDetectorFactory.getDetector().isAvailable(GSON_METADATA);
        }
        return isGsonAvailable;
    }
}
