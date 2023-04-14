package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Emission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Emission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmissionRepository extends JpaRepository<Emission, Long> {
}
