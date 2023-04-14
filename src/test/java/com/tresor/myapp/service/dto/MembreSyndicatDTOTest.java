package com.tresor.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class MembreSyndicatDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MembreSyndicatDTO.class);
        MembreSyndicatDTO membreSyndicatDTO1 = new MembreSyndicatDTO();
        membreSyndicatDTO1.setId(1L);
        MembreSyndicatDTO membreSyndicatDTO2 = new MembreSyndicatDTO();
        assertThat(membreSyndicatDTO1).isNotEqualTo(membreSyndicatDTO2);
        membreSyndicatDTO2.setId(membreSyndicatDTO1.getId());
        assertThat(membreSyndicatDTO1).isEqualTo(membreSyndicatDTO2);
        membreSyndicatDTO2.setId(2L);
        assertThat(membreSyndicatDTO1).isNotEqualTo(membreSyndicatDTO2);
        membreSyndicatDTO1.setId(null);
        assertThat(membreSyndicatDTO1).isNotEqualTo(membreSyndicatDTO2);
    }
}
