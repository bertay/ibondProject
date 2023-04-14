package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class ParametresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Parametres.class);
        Parametres parametres1 = new Parametres();
        parametres1.setId(1L);
        Parametres parametres2 = new Parametres();
        parametres2.setId(parametres1.getId());
        assertThat(parametres1).isEqualTo(parametres2);
        parametres2.setId(2L);
        assertThat(parametres1).isNotEqualTo(parametres2);
        parametres1.setId(null);
        assertThat(parametres1).isNotEqualTo(parametres2);
    }
}
