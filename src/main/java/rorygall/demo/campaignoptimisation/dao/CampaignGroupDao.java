package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.CampaignGroup;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CampaignGroupDao {

    public void saveCampaignGroup(final CampaignGroup campaignGroup);

    public List<CampaignGroup> findAllCampaignGroups();

    public CampaignGroup findCampaignGroupById(final int id);
}
