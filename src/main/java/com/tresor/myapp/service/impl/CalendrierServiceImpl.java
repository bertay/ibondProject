package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.CalendrierService;
import com.tresor.myapp.domain.Calendrier;
import com.tresor.myapp.repository.CalendrierRepository;
import com.tresor.myapp.service.dto.CalendrierDTO;
import com.tresor.myapp.service.mapper.CalendrierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Calendrier}.
 */
@Service
@Transactional
public class CalendrierServiceImpl implements CalendrierService {

    private final Logger log = LoggerFactory.getLogger(CalendrierServiceImpl.class);

    private final CalendrierRepository calendrierRepository;

    private final CalendrierMapper calendrierMapper;

    public CalendrierServiceImpl(CalendrierRepository calendrierRepository, CalendrierMapper calendrierMapper) {
        this.calendrierRepository = calendrierRepository;
        this.calendrierMapper = calendrierMapper;
    }

    @Override
    public CalendrierDTO save(CalendrierDTO calendrierDTO) {
        log.debug("Request to save Calendrier : {}", calendrierDTO);
        Calendrier calendrier = calendrierMapper.toEntity(calendrierDTO);
        calendrier = calendrierRepository.save(calendrier);
        return calendrierMapper.toDto(calendrier);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CalendrierDTO> findAll() {
        log.debug("Request to get all Calendriers");
        return calendrierRepository.findAll().stream()
            .map(calendrierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CalendrierDTO> findOne(Long id) {
        log.debug("Request to get Calendrier : {}", id);
        return calendrierRepository.findById(id)
            .map(calendrierMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Calendrier : {}", id);
        calendrierRepository.deleteById(id);
    }
}
