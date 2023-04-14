package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class NatureTitreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureTitreDTO.class);
        NatureTitreDTO natureTitreDTO1 = new NatureTitreDTO();
        natureTitreDTO1.setId(1L);
        NatureTitreDTO natureTitreDTO2 = new NatureTitreDTO();
        assertThat(natureTitreDTO1).isNotEqualTo(natureTitreDTO2);
        natureTitreDTO2.setId(natureTitreDTO1.getId());
        assertThat(natureTitreDTO1).isEqualTo(natureTitreDTO2);
        natureTitreDTO2.setId(2L);
        assertThat(natureTitreDTO1).isNotEqualTo(natureTitreDTO2);
        natureTitreDTO1.setId(null);
        assertThat(natureTitreDTO1).isNotEqualTo(natureTitreDTO2);
    }
}
