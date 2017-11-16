package com.emcloud.cpi.service.impl;

import com.emcloud.cpi.service.CompointstatusService;
import com.emcloud.cpi.domain.Compointstatus;
import com.emcloud.cpi.repository.CompointstatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Compointstatus.
 */
@Service
@Transactional
public class CompointstatusServiceImpl implements CompointstatusService{

    private final Logger log = LoggerFactory.getLogger(CompointstatusServiceImpl.class);

    private final CompointstatusRepository compointstatusRepository;

    public CompointstatusServiceImpl(CompointstatusRepository compointstatusRepository) {
        this.compointstatusRepository = compointstatusRepository;
    }

    /**
     * Save a compointstatus.
     *
     * @param compointstatus the entity to save
     * @return the persisted entity
     */
    @Override
    public Compointstatus save(Compointstatus compointstatus) {
        log.debug("Request to save Compointstatus : {}", compointstatus);
        return compointstatusRepository.save(compointstatus);
    }

    /**
     *  Get all the compointstatuses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Compointstatus> findAll(Pageable pageable) {
        log.debug("Request to get all Compointstatuses");
        return compointstatusRepository.findAll(pageable);
    }

    /**
     *  Get one compointstatus by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Compointstatus findOne(Long id) {
        log.debug("Request to get Compointstatus : {}", id);
        return compointstatusRepository.findOne(id);
    }

    /**
     *  Delete the  compointstatus by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Compointstatus : {}", id);
        compointstatusRepository.delete(id);
    }
}
