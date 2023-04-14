package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class DetailCalendrierTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailCalendrier.class);
        DetailCalendrier detailCalendrier1 = new DetailCalendrier();
        detailCalendrier1.setId(1L);
        DetailCalendrier detailCalendrier2 = new DetailCalendrier();
        detailCalendrier2.setId(detailCalendrier1.getId());
        assertThat(detailCalendrier1).isEqualTo(detailCalendrier2);
        detailCalendrier2.setId(2L);
        assertThat(detailCalendrier1).isNotEqualTo(detailCalendrier2);
        detailCalendrier1.setId(null);
        assertThat(detailCalendrier1).isNotEqualTo(detailCalendrier2);
    }
}
