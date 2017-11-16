package com.emcloud.cpi.service;

import com.emcloud.cpi.domain.Compoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Compoint.
 */
public interface CompointService {

    /**
     * Save a compoint.
     *
     * @param compoint the entity to save
     * @return the persisted entity
     */
    Compoint save(Compoint compoint);

    /**
     * update a compoint.
     *
     * @param compoint the entity to update
     * @return the persisted entity
     */
    Compoint update(Compoint compoint);

    /**
     *  Get all the compoints.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Compoint> findAll(Pageable pageable);

    /**
     *  Get the "id" compoint.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Compoint findOne(Long id);

    /**
     *  Delete the "id" compoint.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
