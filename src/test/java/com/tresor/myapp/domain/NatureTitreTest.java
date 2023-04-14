package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class NatureTitreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureTitre.class);
        NatureTitre natureTitre1 = new NatureTitre();
        natureTitre1.setId(1L);
        NatureTitre natureTitre2 = new NatureTitre();
        natureTitre2.setId(natureTitre1.getId());
        assertThat(natureTitre1).isEqualTo(natureTitre2);
        natureTitre2.setId(2L);
        assertThat(natureTitre1).isNotEqualTo(natureTitre2);
        natureTitre1.setId(null);
        assertThat(natureTitre1).isNotEqualTo(natureTitre2);
    }
}
