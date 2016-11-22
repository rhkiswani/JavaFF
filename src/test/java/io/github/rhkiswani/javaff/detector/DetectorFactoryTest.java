package io.github.rhkiswani.javaff.detector;

import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DetectorFactoryTest {

    @Test
    public void testRegistry() throws Exception {
        assertThat(ApiDetectorFactory.instance()).isNotNull();
        assertThat(ApiDetectorFactory.instance() == ApiDetectorFactory.instance()).isEqualTo(true);
        assertThat(ApiDetectorFactory.getDetector().isAvailable(ApiDetectorUtil.JPA_API_METADATA)).isEqualTo(true);
        try{
            ApiDetectorFactory.getDetector().isAvailable(null);
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("Api Metadata cant be null");
        }
        try{
            ApiDetectorFactory.getDetector().isAvailable(new ApiMetadata());
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("Api Main Class Name cant be null");
        }

    }


}
