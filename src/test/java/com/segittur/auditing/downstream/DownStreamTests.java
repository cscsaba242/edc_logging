package com.segittur.auditing.downstream;

import com.segittur.auditing.TestHelper;
import com.segittur.auditing.model.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests the web api designed for auditing backend --> clients
 */
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class DownStreamTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    @DynamicPropertySource
    static void setMongoUri(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    /**
     * Db manager
     */
    @Autowired
    private DownStreamRepository downStreamRepository;

    /**
     * Service between web api and the db manager
     */
    @Autowired
    private DownStreamService downStreamService;

    /**
     * Test client calls the endpoints
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Clears the db before every test
     */
    @BeforeEach
    public void setUp() {
        downStreamRepository.deleteAll();
    }

    /**
     * Saves 2 Log records into db and checks them.
     *
     * @throws Exception can be thrown by mock client api call
     */
    @Test
    void findAllTest() throws Exception {
        // GIVEN
        Log testLogA = new Log(TestHelper.getDateTimeStampBy("2025-04-20 12:30:00"), "edcIdA", "providerA", "consumerA",
                "processA", "processStepA", "stepStateA", Map.of("level", "INFO"));
        Log testLogB = new Log(TestHelper.getDateTimeStampBy("2025-04-20 18:30:00"), "edcIdB", "providerB", "consumerB",
                "processB", "processStepB", "stepStateB", Map.of("level", "WARNING"));
        // WHEN
        downStreamRepository.save(testLogA);
        downStreamRepository.save(testLogB);
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/downstream/findAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].providerId").value("providerA"));
    }

    /**
     * Checks the build version endpoint
     * Provides information about current code we built on
     * build version consists the closest.tag.name closest.tag.commit.count
     * commit.id.abbrev
     *
     * @throws Exception by mockMvc perform
     */
    @Test
    void buildVersionTest() throws Exception {
        // THEN
        mockMvc.perform(MockMvcRequestBuilders.get("/downstream/getBuildVersion"))
                .andExpect(status().isOk()).andExpect(content().string(downStreamService.getBuildVersion()));
    }
}
