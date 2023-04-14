package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MembreSyndicatMapperTest {

    private MembreSyndicatMapper membreSyndicatMapper;

    @BeforeEach
    public void setUp() {
        membreSyndicatMapper = new MembreSyndicatMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(membreSyndicatMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(membreSyndicatMapper.fromId(null)).isNull();
    }
}
