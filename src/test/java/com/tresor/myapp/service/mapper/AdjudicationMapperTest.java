package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdjudicationMapperTest {

    private AdjudicationMapper adjudicationMapper;

    @BeforeEach
    public void setUp() {
        adjudicationMapper = new AdjudicationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(adjudicationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(adjudicationMapper.fromId(null)).isNull();
    }
}
