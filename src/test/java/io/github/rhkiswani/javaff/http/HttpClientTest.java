package io.github.rhkiswani.javaff.http;

import io.github.rhkiswani.javaff.exceptions.ExceptionUtil;
import io.github.rhkiswani.javaff.factory.exceptions.NoImplementationFoundException;
import io.github.rhkiswani.javaff.httpclient.ApacheHttpClient;
import io.github.rhkiswani.javaff.httpclient.HttpClient;
import io.github.rhkiswani.javaff.httpclient.HttpClientFactory;
import io.github.rhkiswani.javaff.httpclient.exceptions.HttpClientException;
import io.github.rhkiswani.javaff.json.JsonHandler;
import io.github.rhkiswani.javaff.json.JsonHandlerFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientTest extends WebTester{

    @Test
    public void testFactory() throws Exception {
        // check default
        HttpClient httpClient = HttpClientFactory.getHttpClient(Object.class);
        assertThat(httpClient).isNotNull();

        HttpClientFactory.instance().remove(Object.class);
        try {
            HttpClientFactory.getHttpClient(Object.class);
        }catch (Exception e){
            assertThat(e).isInstanceOf(NoImplementationFoundException.class).hasMessage("No implementation found for HttpClientFactory you need to set implementation through HttpClientFactory.instance().add or add https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient to your classpath");
        }
        HttpClientFactory.instance().add(Object.class, new ApacheHttpClient());
    }

    @Test
    public void testExceptions() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        try {
            httpClient.post("http://xyaaaaaxzcsdrwerwefv.com", null, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(HttpClientException.class);
            ExceptionUtil.handle(e);
        }
        try {
            httpClient.postJson("http://xyaaaaaxzcsdrwerwefv.com", null, null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(HttpClientException.class).hasMessage("Json Object cant be null");
        }
        try {
            httpClient.postJson("http://asdadasdasdasdasasd.com", "{}", null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(HttpClientException.class);
            ExceptionUtil.handle(e);
        }
        try {
            httpClient.postJson("http://asdasd.com", "{}", null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(HttpClientException.class);
            ExceptionUtil.handle(e);
        }
    }


    @Test
    public void testPost() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        Map<String, String> params = prepareParams();
        String post = httpClient.post(BASE_URL, params, null);
        assertValues("POST", params, post);
    }

    @Test
    public void testPostJson() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        String post = httpClient.postJson(BASE_URL,"{\"method\":\"POST\"}", null);
        assertJsonResponse(post, "POST");
    }

    @Test
    public void testPatchJson() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        String post = httpClient.patchJson(BASE_URL,"{\"method\":\"PATCH\"}", null);
        assertJsonResponse(post, "PATCH");
    }

    @Test
    public void testPut() throws Exception{
        String method = "PUT";
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        Map<String, String> params = prepareParams();
        String put = httpClient.put(BASE_URL, params, null);
        assertValues(method, params, put);
    }

    private Map<String, String> prepareParams() {
        Map<String, String> params = new HashMap<>();
        params.put("paramsEmail", "rhkiswani@gmail.com");
        return params;
    }

    private void assertValues(String method, Map<String, String> params, String post) {
        assertValues(method, params, post, "application/x-www-form-urlencoded");
    }

    private void assertValues(String method, Map<String, String> params, String post, String contentType) {
        JsonHandler handler = JsonHandlerFactory.getJsonHandler(HttpClientTest.class);
        Response receivedRespond = handler.fromJson(post, Response.class);

        assertThat(receivedRespond.params).isEqualTo(params);
        assertThat(receivedRespond.method).isEqualTo(method);
        assertThat(receivedRespond.contentType).isEqualTo(contentType);
    }

    @Test
    public void testPutJson() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        String put = httpClient.putJson(BASE_URL,"{\"method\":\"PUT\"}", null);
        assertJsonResponse(put, "PUT");
    }

    @Test
    public void testGet() throws Exception{
        String method = "GET";
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        Map<String, String> params = prepareParams();
        String get = httpClient.get(BASE_URL, params, null);
        assertValues(method, params, get, null);
    }

    @Test
    public void testDelete() throws Exception{
        String method = "DELETE";
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        Map<String, String> params = prepareParams();
        String get = httpClient.delete(BASE_URL, params, null);
        assertValues(method, params, get, null);
    }

    @Test
    public void testHead() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        Map<String, String> params = prepareParams();
        Map<String, String> head = httpClient.head(BASE_URL, params, null);
        assertThat(head.get("Server")).isEqualTo("Jetty(9.4.0.RC2)");
    }

    @Test
    public void testOptions() throws Exception{
        HttpClient httpClient = HttpClientFactory.getHttpClient(HttpClientTest.class);
        Map<String, String> params = prepareParams();
        Map<String, String> head = httpClient.options(BASE_URL, params, null);
        assertThat(head.get("Server")).isEqualTo("Jetty(9.4.0.RC2)");
    }

    private void assertJsonResponse(String response, String method) {
        JsonHandler handler = JsonHandlerFactory.getJsonHandler(HttpClientTest.class);
        Response receivedRespond = handler.fromJson(response, Response.class);

        assertThat(receivedRespond.jsonParams).isEqualTo("{\"method\":\""+ method +"\"}");
        assertThat(receivedRespond.method).isEqualTo(method);
        assertThat(receivedRespond.contentType).isEqualTo("application/json");
    }
}
