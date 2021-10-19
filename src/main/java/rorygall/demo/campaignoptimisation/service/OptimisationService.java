package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.entity.Optimisation;

public interface OptimisationService {

    public Optimisation createOptimisationsForGroup(final int groupId);

    public void createAndSaveOptimisationsForGroup(final int groupId);

    //public Optimisation getOptimisationsForGroup(final int groupId);

    public void applyOptimisationForGroup(final int groupId);
}
