package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class DetailCalendrierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailCalendrierDTO.class);
        DetailCalendrierDTO detailCalendrierDTO1 = new DetailCalendrierDTO();
        detailCalendrierDTO1.setId(1L);
        DetailCalendrierDTO detailCalendrierDTO2 = new DetailCalendrierDTO();
        assertThat(detailCalendrierDTO1).isNotEqualTo(detailCalendrierDTO2);
        detailCalendrierDTO2.setId(detailCalendrierDTO1.getId());
        assertThat(detailCalendrierDTO1).isEqualTo(detailCalendrierDTO2);
        detailCalendrierDTO2.setId(2L);
        assertThat(detailCalendrierDTO1).isNotEqualTo(detailCalendrierDTO2);
        detailCalendrierDTO1.setId(null);
        assertThat(detailCalendrierDTO1).isNotEqualTo(detailCalendrierDTO2);
    }
}
