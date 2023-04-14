package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class AvisEmissionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AvisEmissionDTO.class);
        AvisEmissionDTO avisEmissionDTO1 = new AvisEmissionDTO();
        avisEmissionDTO1.setId(1L);
        AvisEmissionDTO avisEmissionDTO2 = new AvisEmissionDTO();
        assertThat(avisEmissionDTO1).isNotEqualTo(avisEmissionDTO2);
        avisEmissionDTO2.setId(avisEmissionDTO1.getId());
        assertThat(avisEmissionDTO1).isEqualTo(avisEmissionDTO2);
        avisEmissionDTO2.setId(2L);
        assertThat(avisEmissionDTO1).isNotEqualTo(avisEmissionDTO2);
        avisEmissionDTO1.setId(null);
        assertThat(avisEmissionDTO1).isNotEqualTo(avisEmissionDTO2);
    }
}
