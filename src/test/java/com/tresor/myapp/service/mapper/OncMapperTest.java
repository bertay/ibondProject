package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OncMapperTest {

    private OncMapper oncMapper;

    @BeforeEach
    public void setUp() {
        oncMapper = new OncMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(oncMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(oncMapper.fromId(null)).isNull();
    }
}
