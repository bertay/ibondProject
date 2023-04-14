package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Adjudication;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Adjudication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdjudicationRepository extends JpaRepository<Adjudication, Long> {
}
