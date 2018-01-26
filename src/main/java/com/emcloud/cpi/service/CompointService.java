package com.emcloud.cpi.service;

import com.emcloud.cpi.domain.Compoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

//    /**
//     *  Get all the compoints.
//     *
//     *  @param registerName the pagination information
//     *  @return the list of entities
//     */
//    List<Compoint> findByRegisterName(String registerName);

    /**
     *  Get all the compoints.
     *
     *  @return the list of entities
     */
    List<Compoint> findAll();


    /**
     *  Get all the companies.
     *
     *  @param companyCode the pagination information
     *  @return the list of entities
     */
    List<Compoint> findAllByCompanyCode(String companyCode);

    /**
     *  Get the "id" compoint.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Compoint findOne(Long id);

    /**
     *
     *  @return the entity
     */
    Compoint findOne(String  compointCode ,String companyCode);

//    /**
//     *  Get the "id" compoint.
//     *
//     *  @param compointCode the id of the entity
//     *  @return the entity
//     */
//    Compoint findOne(String  compointCode);

    /**
     *  Delete the "id" compoint.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
