package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.ReouvertureService;
import com.tresor.myapp.domain.Reouverture;
import com.tresor.myapp.repository.ReouvertureRepository;
import com.tresor.myapp.service.dto.ReouvertureDTO;
import com.tresor.myapp.service.mapper.ReouvertureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Reouverture}.
 */
@Service
@Transactional
public class ReouvertureServiceImpl implements ReouvertureService {

    private final Logger log = LoggerFactory.getLogger(ReouvertureServiceImpl.class);

    private final ReouvertureRepository reouvertureRepository;

    private final ReouvertureMapper reouvertureMapper;

    public ReouvertureServiceImpl(ReouvertureRepository reouvertureRepository, ReouvertureMapper reouvertureMapper) {
        this.reouvertureRepository = reouvertureRepository;
        this.reouvertureMapper = reouvertureMapper;
    }

    @Override
    public ReouvertureDTO save(ReouvertureDTO reouvertureDTO) {
        log.debug("Request to save Reouverture : {}", reouvertureDTO);
        Reouverture reouverture = reouvertureMapper.toEntity(reouvertureDTO);
        reouverture = reouvertureRepository.save(reouverture);
        return reouvertureMapper.toDto(reouverture);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReouvertureDTO> findAll() {
        log.debug("Request to get all Reouvertures");
        return reouvertureRepository.findAll().stream()
            .map(reouvertureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReouvertureDTO> findOne(Long id) {
        log.debug("Request to get Reouverture : {}", id);
        return reouvertureRepository.findById(id)
            .map(reouvertureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reouverture : {}", id);
        reouvertureRepository.deleteById(id);
    }
}
