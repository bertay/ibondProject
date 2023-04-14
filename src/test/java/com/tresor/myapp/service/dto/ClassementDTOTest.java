package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class ClassementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClassementDTO.class);
        ClassementDTO classementDTO1 = new ClassementDTO();
        classementDTO1.setId(1L);
        ClassementDTO classementDTO2 = new ClassementDTO();
        assertThat(classementDTO1).isNotEqualTo(classementDTO2);
        classementDTO2.setId(classementDTO1.getId());
        assertThat(classementDTO1).isEqualTo(classementDTO2);
        classementDTO2.setId(2L);
        assertThat(classementDTO1).isNotEqualTo(classementDTO2);
        classementDTO1.setId(null);
        assertThat(classementDTO1).isNotEqualTo(classementDTO2);
    }
}
