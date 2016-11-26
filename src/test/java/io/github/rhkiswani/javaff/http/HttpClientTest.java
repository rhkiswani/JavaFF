package io.github.rhkiswani.javaff.http;

import io.github.rhkiswani.javaff.detector.ApiDetectorUtil;
import io.github.rhkiswani.javaff.factory.exceptions.NoImplementationFoundException;
import io.github.rhkiswani.javaff.httpclient.HttpClient;
import io.github.rhkiswani.javaff.httpclient.HttpClientFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
public class HttpClientTest {

    @Test
    @PrepareForTest(ApiDetectorUtil.class)
    public void testFactory() throws Exception {
        // check default
        HttpClient httpClient = HttpClientFactory.getHttpClient(Object.class);
        assertThat(httpClient).isNotNull();

        PowerMockito.mockStatic(ApiDetectorUtil.class);
        PowerMockito.when(ApiDetectorUtil.isApacheHttpClientAvailable()).thenReturn(false);
        // check if the class have been cashed
        HttpClientFactory.getHttpClient(Object.class);
        assertThat(httpClient).isNotNull();
        HttpClientFactory.instance().remove(Object.class);

        try {
            HttpClientFactory.getHttpClient(Object.class);
        }catch (Exception e){
            assertThat(e).isInstanceOf(NoImplementationFoundException.class).hasMessage("No implementation found for HttpClientFactory you need to set implementation through HttpClientFactory.instance().add or add https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient to your classpath");
        }
    }

}
