package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.entity.Recommendation;

import javax.persistence.EntityManager;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    EntityManager entityManager;

    public RecommendationDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void saveRecommendation(final Recommendation recommentdation) {

    }

    @Override
    public List<Recommendation> findAllRecommendations() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Recommendation> theRecommendationGroupQuery = currentSession.createQuery("from Recommendation", Recommendation.class);

        return theRecommendationGroupQuery.getResultList();
    }

    @Override
    public Recommendation findRecommendationById(final int id) {
        return null;
    }
}
