package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RachatMapperTest {

    private RachatMapper rachatMapper;

    @BeforeEach
    public void setUp() {
        rachatMapper = new RachatMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(rachatMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rachatMapper.fromId(null)).isNull();
    }
}
