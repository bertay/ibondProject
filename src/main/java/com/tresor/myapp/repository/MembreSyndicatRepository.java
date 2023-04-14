package com.tresor.myapp.repository;

import com.tresor.myapp.domain.MembreSyndicat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MembreSyndicat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MembreSyndicatRepository extends JpaRepository<MembreSyndicat, Long> {
}
