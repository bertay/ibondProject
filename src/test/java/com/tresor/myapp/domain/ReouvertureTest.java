package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class ReouvertureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reouverture.class);
        Reouverture reouverture1 = new Reouverture();
        reouverture1.setId(1L);
        Reouverture reouverture2 = new Reouverture();
        reouverture2.setId(reouverture1.getId());
        assertThat(reouverture1).isEqualTo(reouverture2);
        reouverture2.setId(2L);
        assertThat(reouverture1).isNotEqualTo(reouverture2);
        reouverture1.setId(null);
        assertThat(reouverture1).isNotEqualTo(reouverture2);
    }
}
