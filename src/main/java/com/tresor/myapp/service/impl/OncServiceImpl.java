package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.OncService;
import com.tresor.myapp.domain.Onc;
import com.tresor.myapp.repository.OncRepository;
import com.tresor.myapp.service.dto.OncDTO;
import com.tresor.myapp.service.mapper.OncMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Onc}.
 */
@Service
@Transactional
public class OncServiceImpl implements OncService {

    private final Logger log = LoggerFactory.getLogger(OncServiceImpl.class);

    private final OncRepository oncRepository;

    private final OncMapper oncMapper;

    public OncServiceImpl(OncRepository oncRepository, OncMapper oncMapper) {
        this.oncRepository = oncRepository;
        this.oncMapper = oncMapper;
    }

    @Override
    public OncDTO save(OncDTO oncDTO) {
        log.debug("Request to save Onc : {}", oncDTO);
        Onc onc = oncMapper.toEntity(oncDTO);
        onc = oncRepository.save(onc);
        return oncMapper.toDto(onc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OncDTO> findAll() {
        log.debug("Request to get all Oncs");
        return oncRepository.findAll().stream()
            .map(oncMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OncDTO> findOne(Long id) {
        log.debug("Request to get Onc : {}", id);
        return oncRepository.findById(id)
            .map(oncMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Onc : {}", id);
        oncRepository.deleteById(id);
    }
}
