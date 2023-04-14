package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class RachatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rachat.class);
        Rachat rachat1 = new Rachat();
        rachat1.setId(1L);
        Rachat rachat2 = new Rachat();
        rachat2.setId(rachat1.getId());
        assertThat(rachat1).isEqualTo(rachat2);
        rachat2.setId(2L);
        assertThat(rachat1).isNotEqualTo(rachat2);
        rachat1.setId(null);
        assertThat(rachat1).isNotEqualTo(rachat2);
    }
}
