package com.tresor.myapp.repository;

import com.tresor.myapp.domain.DetailSoumission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetailSoumission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailSoumissionRepository extends JpaRepository<DetailSoumission, Long> {
}
