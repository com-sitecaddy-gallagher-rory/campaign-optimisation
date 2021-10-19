package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.dao.RecommendationDao;
import rorygall.demo.campaignoptimisation.entity.Recommendation;

import javax.transaction.Transactional;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private RecommendationDao recommendationDao;

    public RecommendationServiceImpl(RecommendationDao recommendationDao) {
        this.recommendationDao = recommendationDao;
    }

    @Override
    @Transactional
    public List<Recommendation> getRecommendationsForOptimisation(final int optimisationId) {
        return recommendationDao.findAllRecommendations();
    }
}
