package com.tresor.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.tresor.myapp.web.rest.TestUtil;

public class ClassementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Classement.class);
        Classement classement1 = new Classement();
        classement1.setId(1L);
        Classement classement2 = new Classement();
        classement2.setId(classement1.getId());
        assertThat(classement1).isEqualTo(classement2);
        classement2.setId(2L);
        assertThat(classement1).isNotEqualTo(classement2);
        classement1.setId(null);
        assertThat(classement1).isNotEqualTo(classement2);
    }
}
