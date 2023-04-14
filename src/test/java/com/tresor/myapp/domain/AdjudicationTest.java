package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class AdjudicationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Adjudication.class);
        Adjudication adjudication1 = new Adjudication();
        adjudication1.setId(1L);
        Adjudication adjudication2 = new Adjudication();
        adjudication2.setId(adjudication1.getId());
        assertThat(adjudication1).isEqualTo(adjudication2);
        adjudication2.setId(2L);
        assertThat(adjudication1).isNotEqualTo(adjudication2);
        adjudication1.setId(null);
        assertThat(adjudication1).isNotEqualTo(adjudication2);
    }
}
