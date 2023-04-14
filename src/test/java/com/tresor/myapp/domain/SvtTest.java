package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class SvtTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Svt.class);
        Svt svt1 = new Svt();
        svt1.setId(1L);
        Svt svt2 = new Svt();
        svt2.setId(svt1.getId());
        assertThat(svt1).isEqualTo(svt2);
        svt2.setId(2L);
        assertThat(svt1).isNotEqualTo(svt2);
        svt1.setId(null);
        assertThat(svt1).isNotEqualTo(svt2);
    }
}
