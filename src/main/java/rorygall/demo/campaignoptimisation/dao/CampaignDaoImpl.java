package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.Campaign;

import javax.persistence.EntityManager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CampaignDaoImpl implements CampaignDao {

    EntityManager entityManager;

    public CampaignDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void saveCampaign(final Campaign campaign) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(campaign);

    }

    @Override
    public List<Campaign> getAllCampaigns() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Campaign> theCampaignQuery = currentSession.createQuery("from Campaign", Campaign.class);

        List<Campaign> allCampaigns = theCampaignQuery.getResultList();

        return allCampaigns;
    }

    @Override
    public Campaign findCampaignById(final int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Campaign.class, id);

    }

}
