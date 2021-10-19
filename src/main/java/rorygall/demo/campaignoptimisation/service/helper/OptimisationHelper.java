package rorygall.demo.campaignoptimisation.service.helper;

import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.entity.Optimisation;
import rorygall.demo.campaignoptimisation.entity.Recommendation;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OptimisationHelper {
    private OptimisationHelper() {
        throw new UnsupportedOperationException();
    }

    public static Optimisation createOptimisationForGroup(final CampaignGroup campaignGroup) throws OptimisationException {
        final Optimisation optimisation = new Optimisation();
        try {
            if (null != campaignGroup && !campaignGroup.getCampaignList().isEmpty()) {
                final List<Recommendation> recommendationList = new ArrayList<>();

                final BigDecimal budgetSum  = campaignGroup.getCampaignList().stream()
                        .map(c -> c.getBudget())
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                final int sumImpressions = campaignGroup.getCampaignList().stream()
                        .map(c -> c.getImpressions())
                        .reduce(0, Integer::sum);

                for (final Campaign c : campaignGroup.getCampaignList()) {
                    final BigDecimal recommendedBudget =
                            new BigDecimal(c.getImpressions()).divide(new BigDecimal(sumImpressions), 2, RoundingMode.HALF_UP).multiply(budgetSum);
                    final Recommendation recommendation = new Recommendation(0, recommendedBudget, optimisation, c);

                    recommendationList.add(recommendation);
                }
                optimisation.setStatus("proposed");
                optimisation.setCampaignGroup(campaignGroup);
                optimisation.setRecommendationsList(recommendationList);
            } else {
                throw new OptimisationException("No Campaigns Found for Group:" + campaignGroup);
            }
        } catch (final Exception exception) {
            throw new OptimisationException(exception);
        }
        return optimisation;
    }
}
