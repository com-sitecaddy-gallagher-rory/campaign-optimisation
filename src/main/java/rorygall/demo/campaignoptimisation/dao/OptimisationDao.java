package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.Optimisation;

import java.util.List;

public interface OptimisationDao {

    public void saveOptimisation(final Optimisation optimisation);

    public List<Optimisation> findAllOptimisations();

    public Optimisation findOptimisationById(final int id);

    public List<Optimisation> findAllOptimisationsForGroup(final int groupId);

}
