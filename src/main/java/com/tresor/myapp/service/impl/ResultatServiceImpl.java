package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.ResultatService;
import com.tresor.myapp.domain.Resultat;
import com.tresor.myapp.repository.ResultatRepository;
import com.tresor.myapp.service.dto.ResultatDTO;
import com.tresor.myapp.service.mapper.ResultatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Resultat}.
 */
@Service
@Transactional
public class ResultatServiceImpl implements ResultatService {

    private final Logger log = LoggerFactory.getLogger(ResultatServiceImpl.class);

    private final ResultatRepository resultatRepository;

    private final ResultatMapper resultatMapper;

    public ResultatServiceImpl(ResultatRepository resultatRepository, ResultatMapper resultatMapper) {
        this.resultatRepository = resultatRepository;
        this.resultatMapper = resultatMapper;
    }

    @Override
    public ResultatDTO save(ResultatDTO resultatDTO) {
        log.debug("Request to save Resultat : {}", resultatDTO);
        Resultat resultat = resultatMapper.toEntity(resultatDTO);
        resultat = resultatRepository.save(resultat);
        return resultatMapper.toDto(resultat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResultatDTO> findAll() {
        log.debug("Request to get all Resultats");
        return resultatRepository.findAll().stream()
            .map(resultatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ResultatDTO> findOne(Long id) {
        log.debug("Request to get Resultat : {}", id);
        return resultatRepository.findById(id)
            .map(resultatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resultat : {}", id);
        resultatRepository.deleteById(id);
    }
}
