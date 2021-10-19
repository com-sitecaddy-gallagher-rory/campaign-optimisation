package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.service.CampaignGroupService;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;


import static org.junit.jupiter.api.Assertions.*;

class CampaignGroupControllerTest {

    @Test
    void testGetAllCampaignGroups() {
        CampaignGroupService campaignGroupService = Mockito.mock(CampaignGroupService.class);
        when(campaignGroupService.findAllCampaignGroups()).thenReturn(Collections.emptyList());
        CampaignGroupController campaignGroupController = new CampaignGroupController(campaignGroupService);
        assertEquals(Collections.emptyList(), (campaignGroupController.getAllCampaignGroups()));
    }

}