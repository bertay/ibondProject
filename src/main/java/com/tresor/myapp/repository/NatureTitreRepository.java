package com.tresor.myapp.repository;

import com.tresor.myapp.domain.NatureTitre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NatureTitre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureTitreRepository extends JpaRepository<NatureTitre, Long> {
}
