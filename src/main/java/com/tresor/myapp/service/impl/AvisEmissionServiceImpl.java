package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.AvisEmissionService;
import com.tresor.myapp.domain.AvisEmission;
import com.tresor.myapp.repository.AvisEmissionRepository;
import com.tresor.myapp.service.dto.AvisEmissionDTO;
import com.tresor.myapp.service.mapper.AvisEmissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AvisEmission}.
 */
@Service
@Transactional
public class AvisEmissionServiceImpl implements AvisEmissionService {

    private final Logger log = LoggerFactory.getLogger(AvisEmissionServiceImpl.class);

    private final AvisEmissionRepository avisEmissionRepository;

    private final AvisEmissionMapper avisEmissionMapper;

    public AvisEmissionServiceImpl(AvisEmissionRepository avisEmissionRepository, AvisEmissionMapper avisEmissionMapper) {
        this.avisEmissionRepository = avisEmissionRepository;
        this.avisEmissionMapper = avisEmissionMapper;
    }

    @Override
    public AvisEmissionDTO save(AvisEmissionDTO avisEmissionDTO) {
        log.debug("Request to save AvisEmission : {}", avisEmissionDTO);
        AvisEmission avisEmission = avisEmissionMapper.toEntity(avisEmissionDTO);
        avisEmission = avisEmissionRepository.save(avisEmission);
        return avisEmissionMapper.toDto(avisEmission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvisEmissionDTO> findAll() {
        log.debug("Request to get all AvisEmissions");
        return avisEmissionRepository.findAll().stream()
            .map(avisEmissionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AvisEmissionDTO> findOne(Long id) {
        log.debug("Request to get AvisEmission : {}", id);
        return avisEmissionRepository.findById(id)
            .map(avisEmissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AvisEmission : {}", id);
        avisEmissionRepository.deleteById(id);
    }
}
