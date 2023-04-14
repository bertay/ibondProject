package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class SoumissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Soumission.class);
        Soumission soumission1 = new Soumission();
        soumission1.setId(1L);
        Soumission soumission2 = new Soumission();
        soumission2.setId(soumission1.getId());
        assertThat(soumission1).isEqualTo(soumission2);
        soumission2.setId(2L);
        assertThat(soumission1).isNotEqualTo(soumission2);
        soumission1.setId(null);
        assertThat(soumission1).isNotEqualTo(soumission2);
    }
}
