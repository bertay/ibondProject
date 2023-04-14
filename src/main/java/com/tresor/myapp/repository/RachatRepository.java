package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Rachat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Rachat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RachatRepository extends JpaRepository<Rachat, Long> {
}
