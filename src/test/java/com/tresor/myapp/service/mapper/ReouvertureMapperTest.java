package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReouvertureMapperTest {

    private ReouvertureMapper reouvertureMapper;

    @BeforeEach
    public void setUp() {
        reouvertureMapper = new ReouvertureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(reouvertureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reouvertureMapper.fromId(null)).isNull();
    }
}
