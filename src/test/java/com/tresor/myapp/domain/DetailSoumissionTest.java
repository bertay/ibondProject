package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class DetailSoumissionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailSoumission.class);
        DetailSoumission detailSoumission1 = new DetailSoumission();
        detailSoumission1.setId(1L);
        DetailSoumission detailSoumission2 = new DetailSoumission();
        detailSoumission2.setId(detailSoumission1.getId());
        assertThat(detailSoumission1).isEqualTo(detailSoumission2);
        detailSoumission2.setId(2L);
        assertThat(detailSoumission1).isNotEqualTo(detailSoumission2);
        detailSoumission1.setId(null);
        assertThat(detailSoumission1).isNotEqualTo(detailSoumission2);
    }
}
