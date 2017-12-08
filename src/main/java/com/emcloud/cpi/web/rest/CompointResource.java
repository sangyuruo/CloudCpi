package com.emcloud.cpi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.emcloud.cpi.domain.Compoint;
import com.emcloud.cpi.service.CompointService;
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
 * REST controller for managing Compoint.
 */
@RestController
@RequestMapping("/api")
public class CompointResource {

    private final Logger log = LoggerFactory.getLogger(CompointResource.class);

    private static final String ENTITY_NAME = "compoint";

    private final CompointService compointService;

    public CompointResource(CompointService compointService) {
        this.compointService = compointService;
    }

    /**
     * POST  /compoints : Create a new compoint.
     *
     * @param compoint the compoint to create
     * @return the ResponseEntity with status 201 (Created) and with body the new compoint, or with status 400 (Bad Request) if the compoint has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/compoints")
    @Timed
    public ResponseEntity<Compoint> createCompoint(@Valid @RequestBody Compoint compoint) throws URISyntaxException {
        log.debug("REST request to save Compoint : {}", compoint);
        if (compoint.getId() != null) {
            throw new BadRequestAlertException("A new compoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Compoint result = compointService.save(compoint);
        return ResponseEntity.created(new URI("/api/compoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /compoints : Updates an existing compoint.
     *
     * @param compoint the compoint to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated compoint,
     * or with status 400 (Bad Request) if the compoint is not valid,
     * or with status 500 (Internal Server Error) if the compoint couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/compoints")
    @Timed
    public ResponseEntity<Compoint> updateCompoint(@Valid @RequestBody Compoint compoint) throws URISyntaxException {
        log.debug("REST request to update Compoint : {}", compoint);
        if (compoint.getId() == null) {
            return createCompoint(compoint);
        }
        Compoint result = compointService.update(compoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, compoint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /compoints : get all the compoints.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compoints in body
     */
    @GetMapping("/compoints")
    @Timed
    public ResponseEntity<List<Compoint>> getAllCompoints(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Compoints");
        Page<Compoint> page = compointService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/compoints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /compoints : get all the compoints.
     *
     * @param companyCode the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of compoints in body
     */
    @GetMapping("/compoints/bycompanyCode")
    @Timed
    public List<Compoint> getAllCompany(@ApiParam String companyCode) {
        log.debug("REST companyCode to get a page of Compoints");
        List<Compoint> list = compointService.findAllCompany(companyCode);
        return list;
    }

    /**
     * GET  /compoints/:id : get the "id" compoint.
     *
     * @param id the id of the compoint to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compoint, or with status 404 (Not Found)
     */
    @GetMapping("/compoints/{id}")
    @Timed
    public ResponseEntity<Compoint> getCompoint(@PathVariable Long id) {
        log.debug("REST request to get Compoint : {}", id);
        Compoint compoint = compointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(compoint));
    }

    /**
     * DELETE  /compoints/:id : delete the "id" compoint.
     *
     * @param id the id of the compoint to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/compoints/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompoint(@PathVariable Long id) {
        log.debug("REST request to delete Compoint : {}", id);
        compointService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
