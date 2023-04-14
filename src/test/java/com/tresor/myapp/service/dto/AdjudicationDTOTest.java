package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class AdjudicationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdjudicationDTO.class);
        AdjudicationDTO adjudicationDTO1 = new AdjudicationDTO();
        adjudicationDTO1.setId(1L);
        AdjudicationDTO adjudicationDTO2 = new AdjudicationDTO();
        assertThat(adjudicationDTO1).isNotEqualTo(adjudicationDTO2);
        adjudicationDTO2.setId(adjudicationDTO1.getId());
        assertThat(adjudicationDTO1).isEqualTo(adjudicationDTO2);
        adjudicationDTO2.setId(2L);
        assertThat(adjudicationDTO1).isNotEqualTo(adjudicationDTO2);
        adjudicationDTO1.setId(null);
        assertThat(adjudicationDTO1).isNotEqualTo(adjudicationDTO2);
    }
}
