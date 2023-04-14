package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SvtMapperTest {

    private SvtMapper svtMapper;

    @BeforeEach
    public void setUp() {
        svtMapper = new SvtMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(svtMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(svtMapper.fromId(null)).isNull();
    }
}
