package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.AdjudicationService;
import com.tresor.myapp.domain.Adjudication;
import com.tresor.myapp.repository.AdjudicationRepository;
import com.tresor.myapp.service.dto.AdjudicationDTO;
import com.tresor.myapp.service.mapper.AdjudicationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Adjudication}.
 */
@Service
@Transactional
public class AdjudicationServiceImpl implements AdjudicationService {

    private final Logger log = LoggerFactory.getLogger(AdjudicationServiceImpl.class);

    private final AdjudicationRepository adjudicationRepository;

    private final AdjudicationMapper adjudicationMapper;

    public AdjudicationServiceImpl(AdjudicationRepository adjudicationRepository, AdjudicationMapper adjudicationMapper) {
        this.adjudicationRepository = adjudicationRepository;
        this.adjudicationMapper = adjudicationMapper;
    }

    @Override
    public AdjudicationDTO save(AdjudicationDTO adjudicationDTO) {
        log.debug("Request to save Adjudication : {}", adjudicationDTO);
        Adjudication adjudication = adjudicationMapper.toEntity(adjudicationDTO);
        adjudication = adjudicationRepository.save(adjudication);
        return adjudicationMapper.toDto(adjudication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdjudicationDTO> findAll() {
        log.debug("Request to get all Adjudications");
        return adjudicationRepository.findAll().stream()
            .map(adjudicationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AdjudicationDTO> findOne(Long id) {
        log.debug("Request to get Adjudication : {}", id);
        return adjudicationRepository.findById(id)
            .map(adjudicationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Adjudication : {}", id);
        adjudicationRepository.deleteById(id);
    }
}
