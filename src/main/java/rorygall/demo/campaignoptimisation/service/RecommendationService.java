package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.entity.Recommendation;

import java.util.List;

public interface RecommendationService {

    public List<Recommendation> getRecommendationsForOptimisation(final int optimisationId);
}
