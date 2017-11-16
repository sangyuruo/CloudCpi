package com.emcloud.cpi.repository;

import com.emcloud.cpi.domain.Compoint;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Compoint entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompointRepository extends JpaRepository<Compoint, Long> {

}
