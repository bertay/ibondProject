package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.DetailSoumissionService;
import com.tresor.myapp.domain.DetailSoumission;
import com.tresor.myapp.repository.DetailSoumissionRepository;
import com.tresor.myapp.service.dto.DetailSoumissionDTO;
import com.tresor.myapp.service.mapper.DetailSoumissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DetailSoumission}.
 */
@Service
@Transactional
public class DetailSoumissionServiceImpl implements DetailSoumissionService {

    private final Logger log = LoggerFactory.getLogger(DetailSoumissionServiceImpl.class);

    private final DetailSoumissionRepository detailSoumissionRepository;

    private final DetailSoumissionMapper detailSoumissionMapper;

    public DetailSoumissionServiceImpl(DetailSoumissionRepository detailSoumissionRepository, DetailSoumissionMapper detailSoumissionMapper) {
        this.detailSoumissionRepository = detailSoumissionRepository;
        this.detailSoumissionMapper = detailSoumissionMapper;
    }

    @Override
    public DetailSoumissionDTO save(DetailSoumissionDTO detailSoumissionDTO) {
        log.debug("Request to save DetailSoumission : {}", detailSoumissionDTO);
        DetailSoumission detailSoumission = detailSoumissionMapper.toEntity(detailSoumissionDTO);
        detailSoumission = detailSoumissionRepository.save(detailSoumission);
        return detailSoumissionMapper.toDto(detailSoumission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetailSoumissionDTO> findAll() {
        log.debug("Request to get all DetailSoumissions");
        return detailSoumissionRepository.findAll().stream()
            .map(detailSoumissionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DetailSoumissionDTO> findOne(Long id) {
        log.debug("Request to get DetailSoumission : {}", id);
        return detailSoumissionRepository.findById(id)
            .map(detailSoumissionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailSoumission : {}", id);
        detailSoumissionRepository.deleteById(id);
    }
}
