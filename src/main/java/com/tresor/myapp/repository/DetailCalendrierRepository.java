package com.tresor.myapp.repository;

import com.tresor.myapp.domain.DetailCalendrier;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetailCalendrier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailCalendrierRepository extends JpaRepository<DetailCalendrier, Long> {
}
