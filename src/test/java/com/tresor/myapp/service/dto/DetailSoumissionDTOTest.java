package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class DetailSoumissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailSoumissionDTO.class);
        DetailSoumissionDTO detailSoumissionDTO1 = new DetailSoumissionDTO();
        detailSoumissionDTO1.setId(1L);
        DetailSoumissionDTO detailSoumissionDTO2 = new DetailSoumissionDTO();
        assertThat(detailSoumissionDTO1).isNotEqualTo(detailSoumissionDTO2);
        detailSoumissionDTO2.setId(detailSoumissionDTO1.getId());
        assertThat(detailSoumissionDTO1).isEqualTo(detailSoumissionDTO2);
        detailSoumissionDTO2.setId(2L);
        assertThat(detailSoumissionDTO1).isNotEqualTo(detailSoumissionDTO2);
        detailSoumissionDTO1.setId(null);
        assertThat(detailSoumissionDTO1).isNotEqualTo(detailSoumissionDTO2);
    }
}
