package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.dao.CampaignGroupDao;
import rorygall.demo.campaignoptimisation.dao.OptimisationDao;
import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.entity.Optimisation;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;
import rorygall.demo.campaignoptimisation.service.helper.OptimisationHelper;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class OptimisationServiceImpl implements OptimisationService {

    private OptimisationDao optimisationDao;
    private CampaignGroupDao campaignGroupDao;

    public OptimisationServiceImpl(final OptimisationDao optimisationDao, final CampaignGroupDao campaignGroupDao) {
        this.optimisationDao = optimisationDao;
        this.campaignGroupDao = campaignGroupDao;
    }

    @Override
    @Transactional
    public Optimisation createOptimisationsForGroup(final int groupId) {
        CampaignGroup campaignGroup = campaignGroupDao.findCampaignGroupById(groupId);
        return OptimisationHelper.createOptimisationForGroup(campaignGroup);
    }

    @Override
    @Transactional
    public void createAndSaveOptimisationsForGroup(final int groupId) {
        Optimisation optimisation = createOptimisationsForGroup(groupId);
        optimisationDao.saveOptimisation(optimisation);
    }

    /*@Override
    @Transactional
    public Optimisation getOptimisationsForGroup(final int groupId) {
        CampaignGroup campaignGroup = campaignGroupDao.findCampaignGroupById(groupId);

    }*/

    @Override
    @Transactional
    public void applyOptimisationForGroup(final int optimisationId) {
        try {
            // Set the status of the optimisation to applied
            Optimisation optimisation = optimisationDao.findOptimisationById(optimisationId);
            optimisation.setStatus("applied");

            // update the budgets of the campaigns with the new values
            Map<Integer, BigDecimal> campaignRecommendations = new HashMap<>(optimisation.getRecommendationsList().size());
            optimisation.getRecommendationsList().stream().forEach(r -> campaignRecommendations.put(r.getCampaign().getId(), r.getBudgetRecommended()));

            for(Campaign campaign: optimisation.getCampaignGroup().getCampaignList())
            {
                campaign.setBudget(campaignRecommendations.get(campaign.getId()));
            }
            optimisationDao.saveOptimisation(optimisation);

        } catch (final Exception exception) {
            throw new OptimisationException("Error applying optmisation", exception);
        }
    }
}
