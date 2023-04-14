package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.RachatService;
import com.tresor.myapp.domain.Rachat;
import com.tresor.myapp.repository.RachatRepository;
import com.tresor.myapp.service.dto.RachatDTO;
import com.tresor.myapp.service.mapper.RachatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Rachat}.
 */
@Service
@Transactional
public class RachatServiceImpl implements RachatService {

    private final Logger log = LoggerFactory.getLogger(RachatServiceImpl.class);

    private final RachatRepository rachatRepository;

    private final RachatMapper rachatMapper;

    public RachatServiceImpl(RachatRepository rachatRepository, RachatMapper rachatMapper) {
        this.rachatRepository = rachatRepository;
        this.rachatMapper = rachatMapper;
    }

    @Override
    public RachatDTO save(RachatDTO rachatDTO) {
        log.debug("Request to save Rachat : {}", rachatDTO);
        Rachat rachat = rachatMapper.toEntity(rachatDTO);
        rachat = rachatRepository.save(rachat);
        return rachatMapper.toDto(rachat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RachatDTO> findAll() {
        log.debug("Request to get all Rachats");
        return rachatRepository.findAll().stream()
            .map(rachatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RachatDTO> findOne(Long id) {
        log.debug("Request to get Rachat : {}", id);
        return rachatRepository.findById(id)
            .map(rachatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Rachat : {}", id);
        rachatRepository.deleteById(id);
    }
}
