package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.bootstrap.DataLoader;
import rorygall.demo.campaignoptimisation.dao.CampaignGroupDao;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
class CampaignGroupServiceImplTestIT {

    @Autowired
    CampaignGroupDao campaignGroupDao;

    CampaignService campaignService;

    CampaignGroupService campaignGroupService;

    @Before
    public void setup() throws Exception {

        DataLoader dataLoader = new DataLoader(campaignService, campaignGroupService);
        dataLoader.run(); // load data

        campaignGroupService = new CampaignGroupServiceImpl(campaignGroupDao);
    }

    @Test
    public void queryCampaignGroup() {
        List<CampaignGroup> campaignGroups = campaignGroupService.findAllCampaignGroups();

        assertNotNull(campaignGroups);
        assertEquals(10, campaignGroups.get(0).getCampaignList());
    }

}