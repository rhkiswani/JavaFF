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

    @Test
    public void testClone() throws Exception {
        assertThat(apiMetadata).isNotNull();
        apiMetadata2.setName("Test");
        apiMetadata2.setFrameworkUrl("URL");
        ApiMetadata clone = apiMetadata2.clone();
        assertThat(clone).isNotNull();
        assertThat(apiMetadata2.getName()).isEqualTo(clone.getName());
        assertThat(apiMetadata2.getGroupId()).isEqualTo(clone.getGroupId());
        assertThat(apiMetadata2.getMainClassName()).isEqualTo(clone.getMainClassName());
        assertThat(apiMetadata2.getFrameworkUrl()).isEqualTo(clone.getFrameworkUrl());
    }


}
