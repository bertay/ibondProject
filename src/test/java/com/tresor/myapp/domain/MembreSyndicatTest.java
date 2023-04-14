package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class MembreSyndicatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MembreSyndicat.class);
        MembreSyndicat membreSyndicat1 = new MembreSyndicat();
        membreSyndicat1.setId(1L);
        MembreSyndicat membreSyndicat2 = new MembreSyndicat();
        membreSyndicat2.setId(membreSyndicat1.getId());
        assertThat(membreSyndicat1).isEqualTo(membreSyndicat2);
        membreSyndicat2.setId(2L);
        assertThat(membreSyndicat1).isNotEqualTo(membreSyndicat2);
        membreSyndicat1.setId(null);
        assertThat(membreSyndicat1).isNotEqualTo(membreSyndicat2);
    }
}
