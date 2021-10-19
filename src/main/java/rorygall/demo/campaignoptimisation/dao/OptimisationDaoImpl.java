package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.Optimisation;

import javax.persistence.EntityManager;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OptimisationDaoImpl implements OptimisationDao {

    EntityManager entityManager;

    public OptimisationDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }


    @Override
    public void saveOptimisation(final Optimisation optimisation) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(optimisation);
    }

    @Override
    public List<Optimisation> findAllOptimisations() {
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Optimisation> theOptimisationQuery = currentSession.createQuery("from Optimisation", Optimisation.class);

        List<Optimisation> allOptimisations = theOptimisationQuery.getResultList();

        return allOptimisations;

    }

    @Override
    public Optimisation findOptimisationById(final int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Optimisation.class, id);
    }

    @Override
    public List<Optimisation> findAllOptimisationsForGroup(final int groupId) {
        return null;
    }
}
