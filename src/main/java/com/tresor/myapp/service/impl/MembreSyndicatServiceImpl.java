package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.MembreSyndicatService;
import com.tresor.myapp.domain.MembreSyndicat;
import com.tresor.myapp.repository.MembreSyndicatRepository;
import com.tresor.myapp.service.dto.MembreSyndicatDTO;
import com.tresor.myapp.service.mapper.MembreSyndicatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MembreSyndicat}.
 */
@Service
@Transactional
public class MembreSyndicatServiceImpl implements MembreSyndicatService {

    private final Logger log = LoggerFactory.getLogger(MembreSyndicatServiceImpl.class);

    private final MembreSyndicatRepository membreSyndicatRepository;

    private final MembreSyndicatMapper membreSyndicatMapper;

    public MembreSyndicatServiceImpl(MembreSyndicatRepository membreSyndicatRepository, MembreSyndicatMapper membreSyndicatMapper) {
        this.membreSyndicatRepository = membreSyndicatRepository;
        this.membreSyndicatMapper = membreSyndicatMapper;
    }

    @Override
    public MembreSyndicatDTO save(MembreSyndicatDTO membreSyndicatDTO) {
        log.debug("Request to save MembreSyndicat : {}", membreSyndicatDTO);
        MembreSyndicat membreSyndicat = membreSyndicatMapper.toEntity(membreSyndicatDTO);
        membreSyndicat = membreSyndicatRepository.save(membreSyndicat);
        return membreSyndicatMapper.toDto(membreSyndicat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MembreSyndicatDTO> findAll() {
        log.debug("Request to get all MembreSyndicats");
        return membreSyndicatRepository.findAll().stream()
            .map(membreSyndicatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MembreSyndicatDTO> findOne(Long id) {
        log.debug("Request to get MembreSyndicat : {}", id);
        return membreSyndicatRepository.findById(id)
            .map(membreSyndicatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MembreSyndicat : {}", id);
        membreSyndicatRepository.deleteById(id);
    }
}
