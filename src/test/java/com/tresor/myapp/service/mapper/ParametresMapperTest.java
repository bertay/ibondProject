package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ParametresMapperTest {

    private ParametresMapper parametresMapper;

    @BeforeEach
    public void setUp() {
        parametresMapper = new ParametresMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(parametresMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(parametresMapper.fromId(null)).isNull();
    }
}
