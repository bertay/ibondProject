package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetailSoumissionMapperTest {

    private DetailSoumissionMapper detailSoumissionMapper;

    @BeforeEach
    public void setUp() {
        detailSoumissionMapper = new DetailSoumissionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detailSoumissionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detailSoumissionMapper.fromId(null)).isNull();
    }
}
