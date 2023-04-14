package com.tresor.myapp.repository;

import com.tresor.myapp.domain.Reouverture;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Reouverture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReouvertureRepository extends JpaRepository<Reouverture, Long> {
}
