package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class OncTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Onc.class);
        Onc onc1 = new Onc();
        onc1.setId(1L);
        Onc onc2 = new Onc();
        onc2.setId(onc1.getId());
        assertThat(onc1).isEqualTo(onc2);
        onc2.setId(2L);
        assertThat(onc1).isNotEqualTo(onc2);
        onc1.setId(null);
        assertThat(onc1).isNotEqualTo(onc2);
    }
}
