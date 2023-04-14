package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class OncDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OncDTO.class);
        OncDTO oncDTO1 = new OncDTO();
        oncDTO1.setId(1L);
        OncDTO oncDTO2 = new OncDTO();
        assertThat(oncDTO1).isNotEqualTo(oncDTO2);
        oncDTO2.setId(oncDTO1.getId());
        assertThat(oncDTO1).isEqualTo(oncDTO2);
        oncDTO2.setId(2L);
        assertThat(oncDTO1).isNotEqualTo(oncDTO2);
        oncDTO1.setId(null);
        assertThat(oncDTO1).isNotEqualTo(oncDTO2);
    }
}
