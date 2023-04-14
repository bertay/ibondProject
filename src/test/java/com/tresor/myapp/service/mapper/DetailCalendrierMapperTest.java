package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailCalendrierMapperTest {

    private DetailCalendrierMapper detailCalendrierMapper;

    @BeforeEach
    public void setUp() {
        detailCalendrierMapper = new DetailCalendrierMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detailCalendrierMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detailCalendrierMapper.fromId(null)).isNull();
    }
}
