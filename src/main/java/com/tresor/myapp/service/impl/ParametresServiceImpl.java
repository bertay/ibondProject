package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.ParametresService;
import com.tresor.myapp.domain.Parametres;
import com.tresor.myapp.repository.ParametresRepository;
import com.tresor.myapp.service.dto.ParametresDTO;
import com.tresor.myapp.service.mapper.ParametresMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Parametres}.
 */
@Service
@Transactional
public class ParametresServiceImpl implements ParametresService {

    private final Logger log = LoggerFactory.getLogger(ParametresServiceImpl.class);

    private final ParametresRepository parametresRepository;

    private final ParametresMapper parametresMapper;

    public ParametresServiceImpl(ParametresRepository parametresRepository, ParametresMapper parametresMapper) {
        this.parametresRepository = parametresRepository;
        this.parametresMapper = parametresMapper;
    }

    @Override
    public ParametresDTO save(ParametresDTO parametresDTO) {
        log.debug("Request to save Parametres : {}", parametresDTO);
        Parametres parametres = parametresMapper.toEntity(parametresDTO);
        parametres = parametresRepository.save(parametres);
        return parametresMapper.toDto(parametres);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParametresDTO> findAll() {
        log.debug("Request to get all Parametres");
        return parametresRepository.findAll().stream()
            .map(parametresMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ParametresDTO> findOne(Long id) {
        log.debug("Request to get Parametres : {}", id);
        return parametresRepository.findById(id)
            .map(parametresMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Parametres : {}", id);
        parametresRepository.deleteById(id);
    }
}
