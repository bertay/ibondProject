package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class SvtDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SvtDTO.class);
        SvtDTO svtDTO1 = new SvtDTO();
        svtDTO1.setId(1L);
        SvtDTO svtDTO2 = new SvtDTO();
        assertThat(svtDTO1).isNotEqualTo(svtDTO2);
        svtDTO2.setId(svtDTO1.getId());
        assertThat(svtDTO1).isEqualTo(svtDTO2);
        svtDTO2.setId(2L);
        assertThat(svtDTO1).isNotEqualTo(svtDTO2);
        svtDTO1.setId(null);
        assertThat(svtDTO1).isNotEqualTo(svtDTO2);
    }
}
