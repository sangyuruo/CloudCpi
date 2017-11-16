package com.emcloud.cpi.web.rest;

import com.emcloud.cpi.EmCloudCpiApp;

import com.emcloud.cpi.config.SecurityBeanOverrideConfiguration;

import com.emcloud.cpi.domain.Compointstatus;
import com.emcloud.cpi.repository.CompointstatusRepository;
import com.emcloud.cpi.service.CompointstatusService;
import com.emcloud.cpi.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.emcloud.cpi.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CompointstatusResource REST controller.
 *
 * @see CompointstatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmCloudCpiApp.class, SecurityBeanOverrideConfiguration.class})
public class CompointstatusResourceIntTest {

    private static final String DEFAULT_COM_POINT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COM_POINT_CODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_COMMUNICATION_STATUS = 1;
    private static final Integer UPDATED_COMMUNICATION_STATUS = 2;

    private static final Instant DEFAULT_RECORD_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECORD_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CompointstatusRepository compointstatusRepository;

    @Autowired
    private CompointstatusService compointstatusService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCompointstatusMockMvc;

    private Compointstatus compointstatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompointstatusResource compointstatusResource = new CompointstatusResource(compointstatusService);
        this.restCompointstatusMockMvc = MockMvcBuilders.standaloneSetup(compointstatusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Compointstatus createEntity(EntityManager em) {
        Compointstatus compointstatus = new Compointstatus()
            .comPointCode(DEFAULT_COM_POINT_CODE)
            .communicationStatus(DEFAULT_COMMUNICATION_STATUS)
            .recordTime(DEFAULT_RECORD_TIME);
        return compointstatus;
    }

    @Before
    public void initTest() {
        compointstatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompointstatus() throws Exception {
        int databaseSizeBeforeCreate = compointstatusRepository.findAll().size();

        // Create the Compointstatus
        restCompointstatusMockMvc.perform(post("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compointstatus)))
            .andExpect(status().isCreated());

        // Validate the Compointstatus in the database
        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeCreate + 1);
        Compointstatus testCompointstatus = compointstatusList.get(compointstatusList.size() - 1);
        assertThat(testCompointstatus.getComPointCode()).isEqualTo(DEFAULT_COM_POINT_CODE);
        assertThat(testCompointstatus.getCommunicationStatus()).isEqualTo(DEFAULT_COMMUNICATION_STATUS);
        assertThat(testCompointstatus.getRecordTime()).isEqualTo(DEFAULT_RECORD_TIME);
    }

    @Test
    @Transactional
    public void createCompointstatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compointstatusRepository.findAll().size();

        // Create the Compointstatus with an existing ID
        compointstatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompointstatusMockMvc.perform(post("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compointstatus)))
            .andExpect(status().isBadRequest());

        // Validate the Compointstatus in the database
        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkComPointCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = compointstatusRepository.findAll().size();
        // set the field null
        compointstatus.setComPointCode(null);

        // Create the Compointstatus, which fails.

        restCompointstatusMockMvc.perform(post("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compointstatus)))
            .andExpect(status().isBadRequest());

        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommunicationStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = compointstatusRepository.findAll().size();
        // set the field null
        compointstatus.setCommunicationStatus(null);

        // Create the Compointstatus, which fails.

        restCompointstatusMockMvc.perform(post("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compointstatus)))
            .andExpect(status().isBadRequest());

        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRecordTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = compointstatusRepository.findAll().size();
        // set the field null
        compointstatus.setRecordTime(null);

        // Create the Compointstatus, which fails.

        restCompointstatusMockMvc.perform(post("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compointstatus)))
            .andExpect(status().isBadRequest());

        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompointstatuses() throws Exception {
        // Initialize the database
        compointstatusRepository.saveAndFlush(compointstatus);

        // Get all the compointstatusList
        restCompointstatusMockMvc.perform(get("/api/compointstatuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compointstatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].comPointCode").value(hasItem(DEFAULT_COM_POINT_CODE.toString())))
            .andExpect(jsonPath("$.[*].communicationStatus").value(hasItem(DEFAULT_COMMUNICATION_STATUS)))
            .andExpect(jsonPath("$.[*].recordTime").value(hasItem(DEFAULT_RECORD_TIME.toString())));
    }

    @Test
    @Transactional
    public void getCompointstatus() throws Exception {
        // Initialize the database
        compointstatusRepository.saveAndFlush(compointstatus);

        // Get the compointstatus
        restCompointstatusMockMvc.perform(get("/api/compointstatuses/{id}", compointstatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(compointstatus.getId().intValue()))
            .andExpect(jsonPath("$.comPointCode").value(DEFAULT_COM_POINT_CODE.toString()))
            .andExpect(jsonPath("$.communicationStatus").value(DEFAULT_COMMUNICATION_STATUS))
            .andExpect(jsonPath("$.recordTime").value(DEFAULT_RECORD_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompointstatus() throws Exception {
        // Get the compointstatus
        restCompointstatusMockMvc.perform(get("/api/compointstatuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompointstatus() throws Exception {
        // Initialize the database
        compointstatusService.save(compointstatus);

        int databaseSizeBeforeUpdate = compointstatusRepository.findAll().size();

        // Update the compointstatus
        Compointstatus updatedCompointstatus = compointstatusRepository.findOne(compointstatus.getId());
        updatedCompointstatus
            .comPointCode(UPDATED_COM_POINT_CODE)
            .communicationStatus(UPDATED_COMMUNICATION_STATUS)
            .recordTime(UPDATED_RECORD_TIME);

        restCompointstatusMockMvc.perform(put("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCompointstatus)))
            .andExpect(status().isOk());

        // Validate the Compointstatus in the database
        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeUpdate);
        Compointstatus testCompointstatus = compointstatusList.get(compointstatusList.size() - 1);
        assertThat(testCompointstatus.getComPointCode()).isEqualTo(UPDATED_COM_POINT_CODE);
        assertThat(testCompointstatus.getCommunicationStatus()).isEqualTo(UPDATED_COMMUNICATION_STATUS);
        assertThat(testCompointstatus.getRecordTime()).isEqualTo(UPDATED_RECORD_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingCompointstatus() throws Exception {
        int databaseSizeBeforeUpdate = compointstatusRepository.findAll().size();

        // Create the Compointstatus

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompointstatusMockMvc.perform(put("/api/compointstatuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compointstatus)))
            .andExpect(status().isCreated());

        // Validate the Compointstatus in the database
        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCompointstatus() throws Exception {
        // Initialize the database
        compointstatusService.save(compointstatus);

        int databaseSizeBeforeDelete = compointstatusRepository.findAll().size();

        // Get the compointstatus
        restCompointstatusMockMvc.perform(delete("/api/compointstatuses/{id}", compointstatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Compointstatus> compointstatusList = compointstatusRepository.findAll();
        assertThat(compointstatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Compointstatus.class);
        Compointstatus compointstatus1 = new Compointstatus();
        compointstatus1.setId(1L);
        Compointstatus compointstatus2 = new Compointstatus();
        compointstatus2.setId(compointstatus1.getId());
        assertThat(compointstatus1).isEqualTo(compointstatus2);
        compointstatus2.setId(2L);
        assertThat(compointstatus1).isNotEqualTo(compointstatus2);
        compointstatus1.setId(null);
        assertThat(compointstatus1).isNotEqualTo(compointstatus2);
    }
}
