package com.tresor.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClassementMapperTest {

    private ClassementMapper classementMapper;

    @BeforeEach
    public void setUp() {
        classementMapper = new ClassementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(classementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(classementMapper.fromId(null)).isNull();
    }
}
