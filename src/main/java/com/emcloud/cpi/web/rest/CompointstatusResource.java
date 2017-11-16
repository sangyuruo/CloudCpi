package com.emcloud.cpi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.emcloud.cpi.domain.Compointstatus;
import com.emcloud.cpi.service.CompointstatusService;
import com.emcloud.cpi.web.rest.errors.BadRequestAlertException;
import com.emcloud.cpi.web.rest.util.HeaderUtil;
import com.emcloud.cpi.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Compointstatus.
 */
@RestController
@RequestMapping("/api")
public class CompointstatusResource {

    private final Logger log = LoggerFactory.getLogger(CompointstatusResource.class);

    private static final String ENTITY_NAME = "compointstatus";

    private final CompointstatusService compointstatusService;

    public CompointstatusResource(CompointstatusService compointstatusService) {
        this.compointstatusService = compointstatusService;
    }

    /**
     * POST  /compointstatuses : Create a new compointstatus.
     *
     * @param compointstatus the compointstatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new compointstatus, or with status 400 (Bad Request) if the compointstatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/compointstatuses")
    @Timed
    public ResponseEntity<Compointstatus> createCompointstatus(@Valid @RequestBody Compointstatus compointstatus) throws URISyntaxException {
        log.debug("REST request to save Compointstatus : {}", compointstatus);
        if (compointstatus.getId() != null) {
            throw new BadRequestAlertException("A new compointstatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Compointstatus result = compointstatusService.save(compointstatus);
        return ResponseEntity.created(new URI("/api/compointstatuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /compointstatuses : Updates an existing compointstatus.
     *
     * @param compointstatus the compointstatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated compointstatus,
     * or with status 400 (Bad Request) if the compointstatus is not valid,
     * or with status 500 (Internal Server Error) if the compointstatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/compointstatuses")
    @Timed
    public ResponseEntity<Compointstatus> updateCompointstatus(@Valid @RequestBody Compointstatus compointstatus) throws URISyntaxException {
        log.debug("REST request to update Compointstatus : {}", compointstatus);
        if (compointstatus.getId() == null) {
            return createCompointstatus(compointstatus);
        }
        Compointstatus result = compointstatusService.save(compointstatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, compointstatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /compointstatuses : get all the compointstatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compointstatuses in body
     */
    @GetMapping("/compointstatuses")
    @Timed
    public ResponseEntity<List<Compointstatus>> getAllCompointstatuses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Compointstatuses");
        Page<Compointstatus> page = compointstatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/compointstatuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /compointstatuses/:id : get the "id" compointstatus.
     *
     * @param id the id of the compointstatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compointstatus, or with status 404 (Not Found)
     */
    @GetMapping("/compointstatuses/{id}")
    @Timed
    public ResponseEntity<Compointstatus> getCompointstatus(@PathVariable Long id) {
        log.debug("REST request to get Compointstatus : {}", id);
        Compointstatus compointstatus = compointstatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(compointstatus));
    }

    /**
     * DELETE  /compointstatuses/:id : delete the "id" compointstatus.
     *
     * @param id the id of the compointstatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/compointstatuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompointstatus(@PathVariable Long id) {
        log.debug("REST request to delete Compointstatus : {}", id);
        compointstatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
