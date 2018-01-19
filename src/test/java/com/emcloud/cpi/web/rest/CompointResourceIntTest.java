//package com.emcloud.cpi.web.rest;
//
//import com.emcloud.cpi.EmCloudCpiApp;
//
//import com.emcloud.cpi.config.SecurityBeanOverrideConfiguration;
//
//import com.emcloud.cpi.domain.Compoint;
//import com.emcloud.cpi.repository.CompointRepository;
//import com.emcloud.cpi.service.CompointService;
//import com.emcloud.cpi.web.rest.errors.ExceptionTranslator;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//import static com.emcloud.cpi.web.rest.TestUtil.createFormattingConversionService;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
///**
// * Test class for the CompointResource REST controller.
// *
// * @see CompointResource
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {EmCloudCpiApp.class, SecurityBeanOverrideConfiguration.class})
//public class CompointResourceIntTest {
//
//    private static final String DEFAULT_COM_POINT_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_COM_POINT_CODE = "BBBBBBBBBB";
//
//    private static final Integer DEFAULT_REGISTER_CODE = 1;
//    private static final Integer UPDATED_REGISTER_CODE = 2;
//
//    private static final String DEFAULT_REGISTER_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_REGISTER_NAME = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ADDRESS_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_ADDRESS_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_ORGANIZATION_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_ORGANIZATION_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_COMPANY_CODE = "AAAAAAAAAA";
//    private static final String UPDATED_COMPANY_CODE = "BBBBBBBBBB";
//
//    private static final String DEFAULT_IP = "AAAAAAAAAA";
//    private static final String UPDATED_IP = "BBBBBBBBBB";
//
//    private static final String DEFAULT_HOST_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_HOST_NAME = "BBBBBBBBBB";
//
//    private static final Integer DEFAULT_HOST_PORT = 1;
//    private static final Integer UPDATED_HOST_PORT = 2;
//
//    private static final Integer DEFAULT_REQUEST_TIMEOUT = 1;
//    private static final Integer UPDATED_REQUEST_TIMEOUT = 2;
//
//    private static final Integer DEFAULT_REPLY_TIMEOUT = 1;
//    private static final Integer UPDATED_REPLY_TIMEOUT = 2;
//
//    private static final Boolean DEFAULT_ENABLE = false;
//    private static final Boolean UPDATED_ENABLE = true;
//
//    private static final Boolean DEFAULT_KEEP_ALIVE = false;
//    private static final Boolean UPDATED_KEEP_ALIVE = true;
//
//    private static final Integer DEFAULT_CONNECT_MODE = 1;
//    private static final Integer UPDATED_CONNECT_MODE = 2;
//
//    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
//    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_CREATE_TIME = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_CREATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
//    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";
//
//    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
//    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);
//
//    @Autowired
//    private CompointRepository compointRepository;
//
//    @Autowired
//    private CompointService compointService;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private ExceptionTranslator exceptionTranslator;
//
//    @Autowired
//    private EntityManager em;
//
//    private MockMvc restCompointMockMvc;
//
//    private Compoint compoint;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        final CompointResource compointResource = new CompointResource(compointService);
//        this.restCompointMockMvc = MockMvcBuilders.standaloneSetup(compointResource)
//            .setCustomArgumentResolvers(pageableArgumentResolver)
//            .setControllerAdvice(exceptionTranslator)
//            .setConversionService(createFormattingConversionService())
//            .setMessageConverters(jacksonMessageConverter).build();
//    }
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Compoint createEntity(EntityManager em) {
//        Compoint compoint = new Compoint(encapsulated)
//            .comPointCode(DEFAULT_COM_POINT_CODE)
//            .registerCode(DEFAULT_REGISTER_CODE)
//            .registerName(DEFAULT_REGISTER_NAME)
//            .addressCode(DEFAULT_ADDRESS_CODE)
//            .organizationCode(DEFAULT_ORGANIZATION_CODE)
//            .companyCode(DEFAULT_COMPANY_CODE)
//            .ip(DEFAULT_IP)
//            .hostName(DEFAULT_HOST_NAME)
//            .hostPort(DEFAULT_HOST_PORT)
//            .requestTimeout(DEFAULT_REQUEST_TIMEOUT)
//            .replyTimeout(DEFAULT_REPLY_TIMEOUT)
//            .enable(DEFAULT_ENABLE)
//            .keepAlive(DEFAULT_KEEP_ALIVE)
//            .connectMode(DEFAULT_CONNECT_MODE)
//            .createdBy(DEFAULT_CREATED_BY)
//            .createTime(DEFAULT_CREATE_TIME)
//            .updatedBy(DEFAULT_UPDATED_BY)
//            .updateTime(DEFAULT_UPDATE_TIME);
//        return compoint;
//    }
//
//    @Before
//    public void initTest() {
//        compoint = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createCompoint() throws Exception {
//        int databaseSizeBeforeCreate = compointRepository.findAll().size();
//
//        // Create the Compoint
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isCreated());
//
//        // Validate the Compoint in the database
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeCreate + 1);
//        Compoint testCompoint = compointList.get(compointList.size() - 1);
//        assertThat(testCompoint.getComPointCode()).isEqualTo(DEFAULT_COM_POINT_CODE);
//        assertThat(testCompoint.getRegisterCode()).isEqualTo(DEFAULT_REGISTER_CODE);
//        assertThat(testCompoint.getRegisterName()).isEqualTo(DEFAULT_REGISTER_NAME);
//        assertThat(testCompoint.getAddressCode()).isEqualTo(DEFAULT_ADDRESS_CODE);
//        assertThat(testCompoint.getOrganizationCode()).isEqualTo(DEFAULT_ORGANIZATION_CODE);
//        assertThat(testCompoint.getCompanyCode()).isEqualTo(DEFAULT_COMPANY_CODE);
//        assertThat(testCompoint.getIp()).isEqualTo(DEFAULT_IP);
//        assertThat(testCompoint.getHostName()).isEqualTo(DEFAULT_HOST_NAME);
//        assertThat(testCompoint.getHostPort()).isEqualTo(DEFAULT_HOST_PORT);
//        assertThat(testCompoint.getRequestTimeout()).isEqualTo(DEFAULT_REQUEST_TIMEOUT);
//        assertThat(testCompoint.getReplyTimeout()).isEqualTo(DEFAULT_REPLY_TIMEOUT);
//        assertThat(testCompoint.isEnable()).isEqualTo(DEFAULT_ENABLE);
//        assertThat(testCompoint.isKeepAlive()).isEqualTo(DEFAULT_KEEP_ALIVE);
//        assertThat(testCompoint.getConnectMode()).isEqualTo(DEFAULT_CONNECT_MODE);
//        assertThat(testCompoint.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
//        assertThat(testCompoint.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);
//        assertThat(testCompoint.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
//        assertThat(testCompoint.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void createCompointWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = compointRepository.findAll().size();
//
//        // Create the Compoint with an existing ID
//        compoint.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Compoint in the database
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    public void checkComPointCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setComPointCode(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkRegisterCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setRegisterCode(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkAddressCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setAddressCode(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkOrganizationCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setOrganizationCode(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCompanyCodeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setCompanyCode(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkIpIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setIp(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkHostNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setHostName(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkHostPortIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setHostPort(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkRequestTimeoutIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setRequestTimeout(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkReplyTimeoutIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setReplyTimeout(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkEnableIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setEnable(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkKeepAliveIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setKeepAlive(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkConnectModeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setConnectMode(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCreatedByIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setCreatedBy(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkCreateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setCreateTime(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkUpdatedByIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setUpdatedBy(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkUpdateTimeIsRequired() throws Exception {
//        int databaseSizeBeforeTest = compointRepository.findAll().size();
//        // set the field null
//        compoint.setUpdateTime(null);
//
//        // Create the Compoint, which fails.
//
//        restCompointMockMvc.perform(post("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isBadRequest());
//
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllCompoints() throws Exception {
//        // Initialize the database
//        compointRepository.saveAndFlush(compoint);
//
//        // Get all the compointList
//        restCompointMockMvc.perform(get("/api/compoints?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(compoint.getId().intValue())))
//            .andExpect(jsonPath("$.[*].comPointCode").value(hasItem(DEFAULT_COM_POINT_CODE.toString())))
//            .andExpect(jsonPath("$.[*].registerCode").value(hasItem(DEFAULT_REGISTER_CODE)))
//            .andExpect(jsonPath("$.[*].registerName").value(hasItem(DEFAULT_REGISTER_NAME.toString())))
//            .andExpect(jsonPath("$.[*].addressCode").value(hasItem(DEFAULT_ADDRESS_CODE.toString())))
//            .andExpect(jsonPath("$.[*].organizationCode").value(hasItem(DEFAULT_ORGANIZATION_CODE.toString())))
//            .andExpect(jsonPath("$.[*].companyCode").value(hasItem(DEFAULT_COMPANY_CODE.toString())))
//            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP.toString())))
//            .andExpect(jsonPath("$.[*].hostName").value(hasItem(DEFAULT_HOST_NAME.toString())))
//            .andExpect(jsonPath("$.[*].hostPort").value(hasItem(DEFAULT_HOST_PORT)))
//            .andExpect(jsonPath("$.[*].requestTimeout").value(hasItem(DEFAULT_REQUEST_TIMEOUT)))
//            .andExpect(jsonPath("$.[*].replyTimeout").value(hasItem(DEFAULT_REPLY_TIMEOUT)))
//            .andExpect(jsonPath("$.[*].enable").value(hasItem(DEFAULT_ENABLE.booleanValue())))
//            .andExpect(jsonPath("$.[*].keepAlive").value(hasItem(DEFAULT_KEEP_ALIVE.booleanValue())))
//            .andExpect(jsonPath("$.[*].connectMode").value(hasItem(DEFAULT_CONNECT_MODE)))
//            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
//            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.toString())))
//            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
//            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getCompoint() throws Exception {
//        // Initialize the database
//        compointRepository.saveAndFlush(compoint);
//
//        // Get the compoint
//        restCompointMockMvc.perform(get("/api/compoints/{id}", compoint.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//            .andExpect(jsonPath("$.id").value(compoint.getId().intValue()))
//            .andExpect(jsonPath("$.comPointCode").value(DEFAULT_COM_POINT_CODE.toString()))
//            .andExpect(jsonPath("$.registerCode").value(DEFAULT_REGISTER_CODE))
//            .andExpect(jsonPath("$.registerName").value(DEFAULT_REGISTER_NAME.toString()))
//            .andExpect(jsonPath("$.addressCode").value(DEFAULT_ADDRESS_CODE.toString()))
//            .andExpect(jsonPath("$.organizationCode").value(DEFAULT_ORGANIZATION_CODE.toString()))
//            .andExpect(jsonPath("$.companyCode").value(DEFAULT_COMPANY_CODE.toString()))
//            .andExpect(jsonPath("$.ip").value(DEFAULT_IP.toString()))
//            .andExpect(jsonPath("$.hostName").value(DEFAULT_HOST_NAME.toString()))
//            .andExpect(jsonPath("$.hostPort").value(DEFAULT_HOST_PORT))
//            .andExpect(jsonPath("$.requestTimeout").value(DEFAULT_REQUEST_TIMEOUT))
//            .andExpect(jsonPath("$.replyTimeout").value(DEFAULT_REPLY_TIMEOUT))
//            .andExpect(jsonPath("$.enable").value(DEFAULT_ENABLE.booleanValue()))
//            .andExpect(jsonPath("$.keepAlive").value(DEFAULT_KEEP_ALIVE.booleanValue()))
//            .andExpect(jsonPath("$.connectMode").value(DEFAULT_CONNECT_MODE))
//            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
//            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.toString()))
//            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
//            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
//    }
//
//    @Test
//    @Transactional
//    public void getNonExistingCompoint() throws Exception {
//        // Get the compoint
//        restCompointMockMvc.perform(get("/api/compoints/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateCompoint() throws Exception {
//        // Initialize the database
//        compointService.save(compoint);
//
//        int databaseSizeBeforeUpdate = compointRepository.findAll().size();
//
//        // Update the compoint
//        Compoint updatedCompoint = compointRepository.findOne(compoint.getId());
//        updatedCompoint
//            .comPointCode(UPDATED_COM_POINT_CODE)
//            .registerCode(UPDATED_REGISTER_CODE)
//            .registerName(UPDATED_REGISTER_NAME)
//            .addressCode(UPDATED_ADDRESS_CODE)
//            .organizationCode(UPDATED_ORGANIZATION_CODE)
//            .companyCode(UPDATED_COMPANY_CODE)
//            .ip(UPDATED_IP)
//            .hostName(UPDATED_HOST_NAME)
//            .hostPort(UPDATED_HOST_PORT)
//            .requestTimeout(UPDATED_REQUEST_TIMEOUT)
//            .replyTimeout(UPDATED_REPLY_TIMEOUT)
//            .enable(UPDATED_ENABLE)
//            .keepAlive(UPDATED_KEEP_ALIVE)
//            .connectMode(UPDATED_CONNECT_MODE)
//            .createdBy(UPDATED_CREATED_BY)
//            .createTime(UPDATED_CREATE_TIME)
//            .updatedBy(UPDATED_UPDATED_BY)
//            .updateTime(UPDATED_UPDATE_TIME);
//
//        restCompointMockMvc.perform(put("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(updatedCompoint)))
//            .andExpect(status().isOk());
//
//        // Validate the Compoint in the database
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeUpdate);
//        Compoint testCompoint = compointList.get(compointList.size() - 1);
//        assertThat(testCompoint.getComPointCode()).isEqualTo(UPDATED_COM_POINT_CODE);
//        assertThat(testCompoint.getRegisterCode()).isEqualTo(UPDATED_REGISTER_CODE);
//        assertThat(testCompoint.getRegisterName()).isEqualTo(UPDATED_REGISTER_NAME);
//        assertThat(testCompoint.getAddressCode()).isEqualTo(UPDATED_ADDRESS_CODE);
//        assertThat(testCompoint.getOrganizationCode()).isEqualTo(UPDATED_ORGANIZATION_CODE);
//        assertThat(testCompoint.getCompanyCode()).isEqualTo(UPDATED_COMPANY_CODE);
//        assertThat(testCompoint.getIp()).isEqualTo(UPDATED_IP);
//        assertThat(testCompoint.getHostName()).isEqualTo(UPDATED_HOST_NAME);
//        assertThat(testCompoint.getHostPort()).isEqualTo(UPDATED_HOST_PORT);
//        assertThat(testCompoint.getRequestTimeout()).isEqualTo(UPDATED_REQUEST_TIMEOUT);
//        assertThat(testCompoint.getReplyTimeout()).isEqualTo(UPDATED_REPLY_TIMEOUT);
//        assertThat(testCompoint.isEnable()).isEqualTo(UPDATED_ENABLE);
//        assertThat(testCompoint.isKeepAlive()).isEqualTo(UPDATED_KEEP_ALIVE);
//        assertThat(testCompoint.getConnectMode()).isEqualTo(UPDATED_CONNECT_MODE);
//        assertThat(testCompoint.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
//        assertThat(testCompoint.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);
//        assertThat(testCompoint.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
//        assertThat(testCompoint.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingCompoint() throws Exception {
//        int databaseSizeBeforeUpdate = compointRepository.findAll().size();
//
//        // Create the Compoint
//
//        // If the entity doesn't have an ID, it will be created instead of just being updated
//        restCompointMockMvc.perform(put("/api/compoints")
//            .contentType(TestUtil.APPLICATION_JSON_UTF8)
//            .content(TestUtil.convertObjectToJsonBytes(compoint)))
//            .andExpect(status().isCreated());
//
//        // Validate the Compoint in the database
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeUpdate + 1);
//    }
//
//    @Test
//    @Transactional
//    public void deleteCompoint() throws Exception {
//        // Initialize the database
//        compointService.save(compoint);
//
//        int databaseSizeBeforeDelete = compointRepository.findAll().size();
//
//        // Get the compoint
//        restCompointMockMvc.perform(delete("/api/compoints/{id}", compoint.getId())
//            .accept(TestUtil.APPLICATION_JSON_UTF8))
//            .andExpect(status().isOk());
//
//        // Validate the database is empty
//        List<Compoint> compointList = compointRepository.findAll();
//        assertThat(compointList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//
//    @Test
//    @Transactional
//    public void equalsVerifier() throws Exception {
//        TestUtil.equalsVerifier(Compoint.class);
//        Compoint compoint1 = new Compoint(encapsulated);
//        compoint1.setId(1L);
//        Compoint compoint2 = new Compoint(encapsulated);
//        compoint2.setId(compoint1.getId());
//        assertThat(compoint1).isEqualTo(compoint2);
//        compoint2.setId(2L);
//        assertThat(compoint1).isNotEqualTo(compoint2);
//        compoint1.setId(null);
//        assertThat(compoint1).isNotEqualTo(compoint2);
//    }
//}
