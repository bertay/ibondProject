package com.tresor.myapp.service.impl;

import com.tresor.myapp.service.ClassementService;
import com.tresor.myapp.domain.Classement;
import com.tresor.myapp.repository.ClassementRepository;
import com.tresor.myapp.service.dto.ClassementDTO;
import com.tresor.myapp.service.mapper.ClassementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Classement}.
 */
@Service
@Transactional
public class ClassementServiceImpl implements ClassementService {

    private final Logger log = LoggerFactory.getLogger(ClassementServiceImpl.class);

    private final ClassementRepository classementRepository;

    private final ClassementMapper classementMapper;

    public ClassementServiceImpl(ClassementRepository classementRepository, ClassementMapper classementMapper) {
        this.classementRepository = classementRepository;
        this.classementMapper = classementMapper;
    }

    @Override
    public ClassementDTO save(ClassementDTO classementDTO) {
        log.debug("Request to save Classement : {}", classementDTO);
        Classement classement = classementMapper.toEntity(classementDTO);
        classement = classementRepository.save(classement);
        return classementMapper.toDto(classement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassementDTO> findAll() {
        log.debug("Request to get all Classements");
        return classementRepository.findAll().stream()
            .map(classementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ClassementDTO> findOne(Long id) {
        log.debug("Request to get Classement : {}", id);
        return classementRepository.findById(id)
            .map(classementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Classement : {}", id);
        classementRepository.deleteById(id);
    }
}
