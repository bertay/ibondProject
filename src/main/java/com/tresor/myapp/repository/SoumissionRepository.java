package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Soumission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Soumission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoumissionRepository extends JpaRepository<Soumission, Long> {
}
