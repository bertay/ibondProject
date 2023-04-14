package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class CalendrierDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CalendrierDTO.class);
        CalendrierDTO calendrierDTO1 = new CalendrierDTO();
        calendrierDTO1.setId(1L);
        CalendrierDTO calendrierDTO2 = new CalendrierDTO();
        assertThat(calendrierDTO1).isNotEqualTo(calendrierDTO2);
        calendrierDTO2.setId(calendrierDTO1.getId());
        assertThat(calendrierDTO1).isEqualTo(calendrierDTO2);
        calendrierDTO2.setId(2L);
        assertThat(calendrierDTO1).isNotEqualTo(calendrierDTO2);
        calendrierDTO1.setId(null);
        assertThat(calendrierDTO1).isNotEqualTo(calendrierDTO2);
    }
}
