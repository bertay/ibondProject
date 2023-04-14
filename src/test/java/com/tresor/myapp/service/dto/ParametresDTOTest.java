package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class ParametresDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParametresDTO.class);
        ParametresDTO parametresDTO1 = new ParametresDTO();
        parametresDTO1.setId(1L);
        ParametresDTO parametresDTO2 = new ParametresDTO();
        assertThat(parametresDTO1).isNotEqualTo(parametresDTO2);
        parametresDTO2.setId(parametresDTO1.getId());
        assertThat(parametresDTO1).isEqualTo(parametresDTO2);
        parametresDTO2.setId(2L);
        assertThat(parametresDTO1).isNotEqualTo(parametresDTO2);
        parametresDTO1.setId(null);
        assertThat(parametresDTO1).isNotEqualTo(parametresDTO2);
    }
}
