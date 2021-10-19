package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.entity.Optimisation;
import rorygall.demo.campaignoptimisation.service.CampaignGroupService;
import rorygall.demo.campaignoptimisation.service.CampaignService;
import rorygall.demo.campaignoptimisation.service.OptimisationService;
import rorygall.demo.campaignoptimisation.service.RecommendationService;

import java.math.BigDecimal;
import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CampaignGroupControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CampaignGroupService campaignGroupService;

    @MockBean
    private CampaignService campaignService;

    @MockBean
    private OptimisationService optimisationService;

    @MockBean
    private RecommendationService recommendationService;

    @Test
    void getAllCampaignGroups() throws Exception {
        when(campaignGroupService
                .findAllCampaignGroups())
                .thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/group"))
                .andExpect(status().isOk());
        verify(campaignGroupService).findAllCampaignGroups();
    }

    @Test
    void getCampaignGroup() throws Exception {
        when(campaignGroupService
                .findCampaignGroupById(1))
                .thenReturn(new CampaignGroup(1,"Group1", Collections.emptyList(), Collections.emptyList()));
        mockMvc.perform(get("/api/group/1"))
                .andExpect(status().isOk());
        verify(campaignGroupService).findCampaignGroupById(1);
    }

    @Test
    void getCampaign() throws Exception {
        when(campaignService
                .findCampaignById(1))
                .thenReturn(new Campaign(0,"Campaign1", new BigDecimal(100.00), 300, new BigDecimal(3000.00), null, null));
        mockMvc.perform(get("/api/campaign/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", Matchers.is("Campaign1")))
                .andExpect(jsonPath("impressions", Matchers.is(300)));
        verify(campaignService).findCampaignById(1);
    }

    @Test
    void getOptimisation() throws Exception {
        when(optimisationService
                .createOptimisationsForGroup(1))
                .thenReturn(new Optimisation(1,"proposed", new CampaignGroup(1,"Group1",
                        Collections.emptyList(), Collections.emptyList()), Collections.emptyList()));
        mockMvc.perform(get("/api/optimisation/create/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("status", Matchers.is("proposed")))
                .andExpect(jsonPath("recommendationsList", Matchers.is(Collections.emptyList())));
        verify(optimisationService).createOptimisationsForGroup(1);

    }
}