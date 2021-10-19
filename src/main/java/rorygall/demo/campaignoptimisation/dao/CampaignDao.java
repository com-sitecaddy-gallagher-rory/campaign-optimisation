package rorygall.demo.campaignoptimisation.dao;

import rorygall.demo.campaignoptimisation.entity.Campaign;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CampaignDao {

    public void saveCampaign(final Campaign campaign);

    public List<Campaign> getAllCampaigns();

    public Campaign findCampaignById(final int id);
}
