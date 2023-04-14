package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class AvisEmissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvisEmission.class);
        AvisEmission avisEmission1 = new AvisEmission();
        avisEmission1.setId(1L);
        AvisEmission avisEmission2 = new AvisEmission();
        avisEmission2.setId(avisEmission1.getId());
        assertThat(avisEmission1).isEqualTo(avisEmission2);
        avisEmission2.setId(2L);
        assertThat(avisEmission1).isNotEqualTo(avisEmission2);
        avisEmission1.setId(null);
        assertThat(avisEmission1).isNotEqualTo(avisEmission2);
    }
}
