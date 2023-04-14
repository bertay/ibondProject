package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.SvtService;
import com.tresor.myapp.domain.Svt;
import com.tresor.myapp.repository.SvtRepository;
import com.tresor.myapp.service.dto.SvtDTO;
import com.tresor.myapp.service.mapper.SvtMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Svt}.
 */
@Service
@Transactional
public class SvtServiceImpl implements SvtService {

    private final Logger log = LoggerFactory.getLogger(SvtServiceImpl.class);

    private final SvtRepository svtRepository;

    private final SvtMapper svtMapper;

    public SvtServiceImpl(SvtRepository svtRepository, SvtMapper svtMapper) {
        this.svtRepository = svtRepository;
        this.svtMapper = svtMapper;
    }

    @Override
    public SvtDTO save(SvtDTO svtDTO) {
        log.debug("Request to save Svt : {}", svtDTO);
        Svt svt = svtMapper.toEntity(svtDTO);
        svt = svtRepository.save(svt);
        return svtMapper.toDto(svt);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SvtDTO> findAll() {
        log.debug("Request to get all Svts");
        return svtRepository.findAll().stream()
            .map(svtMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SvtDTO> findOne(Long id) {
        log.debug("Request to get Svt : {}", id);
        return svtRepository.findById(id)
            .map(svtMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Svt : {}", id);
        svtRepository.deleteById(id);
    }
}
