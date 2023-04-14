package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class RachatDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RachatDTO.class);
        RachatDTO rachatDTO1 = new RachatDTO();
        rachatDTO1.setId(1L);
        RachatDTO rachatDTO2 = new RachatDTO();
        assertThat(rachatDTO1).isNotEqualTo(rachatDTO2);
        rachatDTO2.setId(rachatDTO1.getId());
        assertThat(rachatDTO1).isEqualTo(rachatDTO2);
        rachatDTO2.setId(2L);
        assertThat(rachatDTO1).isNotEqualTo(rachatDTO2);
        rachatDTO1.setId(null);
        assertThat(rachatDTO1).isNotEqualTo(rachatDTO2);
    }
}
