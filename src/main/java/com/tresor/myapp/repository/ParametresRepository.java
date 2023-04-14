package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Parametres;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Parametres entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParametresRepository extends JpaRepository<Parametres, Long> {
}
