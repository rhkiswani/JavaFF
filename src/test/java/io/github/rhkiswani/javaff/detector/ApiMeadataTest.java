package io.github.rhkiswani.javaff.detector;

import org.junit.Before;
import org.junit.Test;
import static io.github.rhkiswani.javaff.detector.ApiDetectorUtil.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMeadataTest {
    private ApiMetadata apiMetadata;
    private ApiMetadata apiMetadata2;

    @Before
    public void setup(){
        apiMetadata = new ApiMetadata(null, null, null, null);
        apiMetadata2 = JACKSON_API_METADATA;
    }

    @Test
    public void testEquals() throws Exception {
        assertThat(apiMetadata.equals(apiMetadata2)).isEqualTo(false);
        apiMetadata.setMainClassName(JACKSON_API_METADATA.getMainClassName());
        assertThat(apiMetadata.equals(apiMetadata2)).isEqualTo(false);
        apiMetadata.setGroupId(JACKSON_API_METADATA.getGroupId());
        assertThat(apiMetadata.equals(apiMetadata2)).isEqualTo(true);
    }


}
