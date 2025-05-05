package com.segittur.auditing.upstream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.segittur.auditing.TestHelper;
import com.segittur.auditing.model.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for data stream between EDC -> DB
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UpStreamTests {
    @Autowired
    private UpStreamRepository upStreamRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        upStreamRepository.deleteAll();
    }

    @Test
    void saveTest() throws Exception {
        // GIVEN
        int size = upStreamRepository.findAll().size();
        Assert.isTrue(size == 0, "Initially we have no any record.");
        Log testLogA = new Log(TestHelper.getDateTimeStampBy("2025-04-20 12:30:00"), "edcIdA", "providerA", "consumerA",
                "processA", "processStepA", "stepStateA", Map.of("level", "INFO"));
        String strTestLogA = objectMapper.writeValueAsString(testLogA);
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/upstream/save").contentType(MediaType.APPLICATION_JSON).content(strTestLogA))
                .andExpect(status().isCreated());
        // THEN
        List<Log> all = upStreamRepository.findAll();
        size = all.size();
        Assert.isTrue(size == 1, "We have one record after saving one Log via api.");
        Assert.isTrue(all.get(0).getProviderId().equals("providerA"), "We saved Log with providerId: providerA");
    }
}
