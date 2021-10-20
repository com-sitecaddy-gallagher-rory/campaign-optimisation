package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.CampaignGroup;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface CampaignGroupDao {

    public void saveCampaignGroup(final CampaignGroup campaignGroup);

    public List<CampaignGroup> findAllCampaignGroups();

    public Optional<CampaignGroup> findCampaignGroupById(final int id);
}
