package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SoumissionMapperTest {

    private SoumissionMapper soumissionMapper;

    @BeforeEach
    public void setUp() {
        soumissionMapper = new SoumissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(soumissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(soumissionMapper.fromId(null)).isNull();
    }
}
