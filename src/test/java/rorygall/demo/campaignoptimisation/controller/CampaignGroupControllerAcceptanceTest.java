package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.resource.OptimisationException;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CampaignGroupControllerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCampaignGroups() throws Exception {

        mockMvc.perform(get("/api/group"))
                .andExpect(status().isOk());
    }

    @Test
    void testExceptionThrownWhenRequestForBadGroupId() throws Exception {

        mockMvc.perform(get("/api/group/999"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof OptimisationException))
                .andExpect(result -> assertEquals("No Group with Id:999", result.getResolvedException().getMessage()))
                .andExpect(status().isNotFound());
    }
}