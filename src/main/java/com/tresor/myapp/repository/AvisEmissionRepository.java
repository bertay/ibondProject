package com.tresor.myapp.repository;

import com.tresor.myapp.domain.AvisEmission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AvisEmission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvisEmissionRepository extends JpaRepository<AvisEmission, Long> {
}
