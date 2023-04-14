package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NatureTitreMapperTest {

    private NatureTitreMapper natureTitreMapper;

    @BeforeEach
    public void setUp() {
        natureTitreMapper = new NatureTitreMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(natureTitreMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(natureTitreMapper.fromId(null)).isNull();
    }
}
