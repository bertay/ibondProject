package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class ReouvertureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReouvertureDTO.class);
        ReouvertureDTO reouvertureDTO1 = new ReouvertureDTO();
        reouvertureDTO1.setId(1L);
        ReouvertureDTO reouvertureDTO2 = new ReouvertureDTO();
        assertThat(reouvertureDTO1).isNotEqualTo(reouvertureDTO2);
        reouvertureDTO2.setId(reouvertureDTO1.getId());
        assertThat(reouvertureDTO1).isEqualTo(reouvertureDTO2);
        reouvertureDTO2.setId(2L);
        assertThat(reouvertureDTO1).isNotEqualTo(reouvertureDTO2);
        reouvertureDTO1.setId(null);
        assertThat(reouvertureDTO1).isNotEqualTo(reouvertureDTO2);
    }
}
