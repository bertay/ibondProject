package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.SoumissionService;
import com.tresor.myapp.domain.Soumission;
import com.tresor.myapp.repository.SoumissionRepository;
import com.tresor.myapp.service.dto.SoumissionDTO;
import com.tresor.myapp.service.mapper.SoumissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Soumission}.
 */
@Service
@Transactional
public class SoumissionServiceImpl implements SoumissionService {

    private final Logger log = LoggerFactory.getLogger(SoumissionServiceImpl.class);

    private final SoumissionRepository soumissionRepository;

    private final SoumissionMapper soumissionMapper;

    public SoumissionServiceImpl(SoumissionRepository soumissionRepository, SoumissionMapper soumissionMapper) {
        this.soumissionRepository = soumissionRepository;
        this.soumissionMapper = soumissionMapper;
    }

    @Override
    public SoumissionDTO save(SoumissionDTO soumissionDTO) {
        log.debug("Request to save Soumission : {}", soumissionDTO);
        Soumission soumission = soumissionMapper.toEntity(soumissionDTO);
        soumission = soumissionRepository.save(soumission);
        return soumissionMapper.toDto(soumission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SoumissionDTO> findAll() {
        log.debug("Request to get all Soumissions");
        return soumissionRepository.findAll().stream()
            .map(soumissionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SoumissionDTO> findOne(Long id) {
        log.debug("Request to get Soumission : {}", id);
        return soumissionRepository.findById(id)
            .map(soumissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Soumission : {}", id);
        soumissionRepository.deleteById(id);
    }
}
