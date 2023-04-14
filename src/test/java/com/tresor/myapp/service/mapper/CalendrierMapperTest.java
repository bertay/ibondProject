package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CalendrierMapperTest {

    private CalendrierMapper calendrierMapper;

    @BeforeEach
    public void setUp() {
        calendrierMapper = new CalendrierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(calendrierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(calendrierMapper.fromId(null)).isNull();
    }
}
