package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AvisEmissionMapperTest {

    private AvisEmissionMapper avisEmissionMapper;

    @BeforeEach
    public void setUp() {
        avisEmissionMapper = new AvisEmissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(avisEmissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(avisEmissionMapper.fromId(null)).isNull();
    }
}
