package com.emcloud.cpi.service.impl;

import com.emcloud.cpi.security.SecurityUtils;
import com.emcloud.cpi.service.CompointService;
import com.emcloud.cpi.domain.Compoint;
import com.emcloud.cpi.repository.CompointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


/**
 * Service Implementation for managing Compoint.
 */
@Service
@Transactional
public class CompointServiceImpl implements CompointService{

    private final Logger log = LoggerFactory.getLogger(CompointServiceImpl.class);

    private final CompointRepository compointRepository;

    public CompointServiceImpl(CompointRepository compointRepository) {
        this.compointRepository = compointRepository;
    }

    /**
     * Save a compoint.
     *
     * @param compoint the entity to save
     * @return the persisted entity
     */
    @Override
    public Compoint save(Compoint compoint) {
        log.debug("Request to save Compoint : {}", compoint);
        compoint.setCreatedBy(SecurityUtils.getCurrentUserLogin());
        compoint.setCreateTime(Instant.now());
        compoint.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        compoint.setUpdateTime(Instant.now());
        compoint.setComPointCode(UUID.randomUUID().toString());
        compoint.setEncapsulated(false);
        compoint.setEmail(SecurityUtils.getCurrentUserLogin());
        return compointRepository.save(compoint);
    }

    /**
     * update a compoint.
     *
     * @param compoint the entity to update
     * @return the persisted entity
     */
    @Override
    public Compoint update(Compoint compoint) {
        log.debug("Request to update Compoint : {}", compoint);
        compoint.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        compoint.setUpdateTime(Instant.now());
        return compointRepository.save(compoint);
    }

    /**
     *  Get all the compoints.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Compoint> findAll(Pageable pageable) {
        log.debug("Request to get all Compoints");
        return compointRepository.findAll(pageable);
    }
    /**
     *  Get all the compoints.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Compoint> findAll() {
        log.debug("Request to get all Compoints");
        return compointRepository.findAll();
    }

    /**
     *  Get all the compoints.
     *
     *  @param companyCode the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Compoint> findAllByCompanyCode(String companyCode) {
        log.debug("Request to get all Companies");
        return compointRepository.findAllByCompanyCode(companyCode);
    }

    /**
     *  Get all the compoints.
     *
     *  @param registerName the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Compoint> findByRegisterName(String registerName){
        log.debug("Request to get all Compoint by registerName");
        return compointRepository.findByRegisterName(registerName);
    }

    /**
     *  Get one compoint by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Compoint findOne(Long id) {
        log.debug("Request to get Compoint : {}", id);
        return compointRepository.findOne(id);
    }

    @Override
    public Compoint findOne(String compointCode, String companyCode) {
        return compointRepository.findByComPointCodeAndCompanyCode(compointCode,companyCode);
    }


    /**
     *  Get one compoint by id.
     *
     *  @param compotinID the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Compoint findOne(String compotinID) {
        log.debug("Request to get Compoint : {}", compotinID);
        return compointRepository.findByComPointCode(compotinID);
    }

    /**
     *  Delete the  compoint by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Compoint : {}", id);
        compointRepository.delete(id);
    }
}
