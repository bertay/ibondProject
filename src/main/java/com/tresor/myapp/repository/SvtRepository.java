package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Svt;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Svt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SvtRepository extends JpaRepository<Svt, Long> {
}
