package com.emcloud.cpi.service;

import com.emcloud.cpi.domain.Compointstatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Compointstatus.
 */
public interface CompointstatusService {

    /**
     * Save a compointstatus.
     *
     * @param compointstatus the entity to save
     * @return the persisted entity
     */
    Compointstatus save(Compointstatus compointstatus);

    /**
     *  Get all the compointstatuses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Compointstatus> findAll(Pageable pageable);

    /**
     *  Get the "id" compointstatus.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Compointstatus findOne(Long id);

    /**
     *  Delete the "id" compointstatus.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
