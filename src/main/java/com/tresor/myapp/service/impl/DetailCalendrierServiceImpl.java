package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.DetailCalendrierService;
import com.tresor.myapp.domain.DetailCalendrier;
import com.tresor.myapp.repository.DetailCalendrierRepository;
import com.tresor.myapp.service.dto.DetailCalendrierDTO;
import com.tresor.myapp.service.mapper.DetailCalendrierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link DetailCalendrier}.
 */
@Service
@Transactional
public class DetailCalendrierServiceImpl implements DetailCalendrierService {

    private final Logger log = LoggerFactory.getLogger(DetailCalendrierServiceImpl.class);

    private final DetailCalendrierRepository detailCalendrierRepository;

    private final DetailCalendrierMapper detailCalendrierMapper;

    public DetailCalendrierServiceImpl(DetailCalendrierRepository detailCalendrierRepository, DetailCalendrierMapper detailCalendrierMapper) {
        this.detailCalendrierRepository = detailCalendrierRepository;
        this.detailCalendrierMapper = detailCalendrierMapper;
    }

    @Override
    public DetailCalendrierDTO save(DetailCalendrierDTO detailCalendrierDTO) {
        log.debug("Request to save DetailCalendrier : {}", detailCalendrierDTO);
        DetailCalendrier detailCalendrier = detailCalendrierMapper.toEntity(detailCalendrierDTO);
        detailCalendrier = detailCalendrierRepository.save(detailCalendrier);
        return detailCalendrierMapper.toDto(detailCalendrier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetailCalendrierDTO> findAll() {
        log.debug("Request to get all DetailCalendriers");
        return detailCalendrierRepository.findAll().stream()
            .map(detailCalendrierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DetailCalendrierDTO> findOne(Long id) {
        log.debug("Request to get DetailCalendrier : {}", id);
        return detailCalendrierRepository.findById(id)
            .map(detailCalendrierMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailCalendrier : {}", id);
        detailCalendrierRepository.deleteById(id);
    }
}
