package rorygall.demo.campaignoptimisation.service.helper;

import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.entity.Optimisation;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO Add more tests

class OptimisationHelperTest {

    @Test
    void createOptimisationForGroup() {

        final Campaign campaign1 = new Campaign(0, "Capaign1", new BigDecimal(100), 10000, new BigDecimal(100000), null, null);
        final Campaign campaign2 = new Campaign(0, "Capaign2", new BigDecimal(400), 1000, new BigDecimal(40000), null, null);
        final Campaign campaign3 = new Campaign(0, "Capaign3", new BigDecimal(800), 15000, new BigDecimal(150000), null, null);
        final Campaign campaign4 = new Campaign(0, "Capaign4", new BigDecimal(200), 20000, new BigDecimal(200000), null, null);
        final Campaign campaign5 = new Campaign(0, "Capaign5", new BigDecimal(600), 12000, new BigDecimal(120000), null, null);

        final List<Campaign> campaignList = new ArrayList<>(5);
        campaignList.add(campaign1);
        campaignList.add(campaign2);
        campaignList.add(campaign3);
        campaignList.add(campaign4);
        campaignList.add(campaign5);

        final CampaignGroup campaignGroup = new CampaignGroup(0, "Group1", campaignList, null);

        Optimisation optimisation = OptimisationHelper.createOptimisationForGroup(campaignGroup);
        assertNotNull(optimisation);
        assertNotNull(optimisation.getRecommendationsList());
        assertTrue(optimisation.getRecommendationsList().size() == 5);

        // Total Impressions = 58K
        // Total Budget = 2100
        // Round the expected values using 2 places for division
        assertEquals(new BigDecimal(357.00).setScale(2), optimisation.getRecommendationsList().get(0).getBudgetRecommended());
        assertEquals(new BigDecimal(42.00).setScale(2), optimisation.getRecommendationsList().get(1).getBudgetRecommended());
        assertEquals(new BigDecimal(546.00).setScale(2), optimisation.getRecommendationsList().get(2).getBudgetRecommended());
        assertEquals(new BigDecimal(714.00).setScale(2), optimisation.getRecommendationsList().get(3).getBudgetRecommended());
        assertEquals(new BigDecimal(441.00).setScale(2), optimisation.getRecommendationsList().get(4).getBudgetRecommended());
    }

    @Test
    void createOptimisationForGroupThrowsExceptionForNullCampaigns() {
        final CampaignGroup campaignGroup = new CampaignGroup(0, "Group1", null, null);

        Throwable exception = assertThrows(OptimisationException.class, () -> OptimisationHelper.createOptimisationForGroup(campaignGroup) );
        assertEquals("java.lang.NullPointerException", exception.getMessage());
    }

}