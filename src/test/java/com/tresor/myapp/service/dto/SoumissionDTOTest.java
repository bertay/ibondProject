package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class SoumissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SoumissionDTO.class);
        SoumissionDTO soumissionDTO1 = new SoumissionDTO();
        soumissionDTO1.setId(1L);
        SoumissionDTO soumissionDTO2 = new SoumissionDTO();
        assertThat(soumissionDTO1).isNotEqualTo(soumissionDTO2);
        soumissionDTO2.setId(soumissionDTO1.getId());
        assertThat(soumissionDTO1).isEqualTo(soumissionDTO2);
        soumissionDTO2.setId(2L);
        assertThat(soumissionDTO1).isNotEqualTo(soumissionDTO2);
        soumissionDTO1.setId(null);
        assertThat(soumissionDTO1).isNotEqualTo(soumissionDTO2);
    }
}
