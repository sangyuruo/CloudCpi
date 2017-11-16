package com.emcloud.cpi.repository;

import com.emcloud.cpi.domain.Compointstatus;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Compointstatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompointstatusRepository extends JpaRepository<Compointstatus, Long> {

}
