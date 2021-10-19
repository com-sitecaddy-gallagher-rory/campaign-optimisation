package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.CampaignGroup;

import javax.persistence.EntityManager;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CampaignGroupDaoImpl implements CampaignGroupDao {

    EntityManager entityManager;

    public CampaignGroupDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void saveCampaignGroup(final CampaignGroup campaignGroup) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(campaignGroup);

    }

    @Override
    public List<CampaignGroup> findAllCampaignGroups() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<CampaignGroup> theCampaignGroupQuery = currentSession.createQuery("from CampaignGroup", CampaignGroup.class);

        return theCampaignGroupQuery.getResultList();
    }

    @Override
    public CampaignGroup findCampaignGroupById(final int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(CampaignGroup.class, id);
    }
}
