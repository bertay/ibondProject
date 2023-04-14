package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.NatureTitreService;
import com.tresor.myapp.domain.NatureTitre;
import com.tresor.myapp.repository.NatureTitreRepository;
import com.tresor.myapp.service.dto.NatureTitreDTO;
import com.tresor.myapp.service.mapper.NatureTitreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NatureTitre}.
 */
@Service
@Transactional
public class NatureTitreServiceImpl implements NatureTitreService {

    private final Logger log = LoggerFactory.getLogger(NatureTitreServiceImpl.class);

    private final NatureTitreRepository natureTitreRepository;

    private final NatureTitreMapper natureTitreMapper;

    public NatureTitreServiceImpl(NatureTitreRepository natureTitreRepository, NatureTitreMapper natureTitreMapper) {
        this.natureTitreRepository = natureTitreRepository;
        this.natureTitreMapper = natureTitreMapper;
    }

    @Override
    public NatureTitreDTO save(NatureTitreDTO natureTitreDTO) {
        log.debug("Request to save NatureTitre : {}", natureTitreDTO);
        NatureTitre natureTitre = natureTitreMapper.toEntity(natureTitreDTO);
        natureTitre = natureTitreRepository.save(natureTitre);
        return natureTitreMapper.toDto(natureTitre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NatureTitreDTO> findAll() {
        log.debug("Request to get all NatureTitres");
        return natureTitreRepository.findAll().stream()
            .map(natureTitreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<NatureTitreDTO> findOne(Long id) {
        log.debug("Request to get NatureTitre : {}", id);
        return natureTitreRepository.findById(id)
            .map(natureTitreMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NatureTitre : {}", id);
        natureTitreRepository.deleteById(id);
    }
}
