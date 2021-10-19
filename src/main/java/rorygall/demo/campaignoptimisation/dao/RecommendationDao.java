package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.Recommendation;

import java.util.List;

public interface RecommendationDao {

    public void saveRecommendation(final Recommendation recommentdation);

    public List<Recommendation> findAllRecommendations();

    public Recommendation findRecommendationById(final int id);
}
