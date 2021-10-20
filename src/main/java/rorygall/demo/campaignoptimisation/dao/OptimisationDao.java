package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.Optimisation;

import java.util.List;
import java.util.Optional;

public interface OptimisationDao {

    public void saveOptimisation(final Optimisation optimisation);

    public List<Optimisation> findAllOptimisations();

    public Optional<Optimisation> findOptimisationById(final int id);

    public List<Optimisation> findAllOptimisationsForGroup(final int groupId);

}
