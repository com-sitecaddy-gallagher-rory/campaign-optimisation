package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.entity.Campaign;

import java.util.List;

public interface CampaignService {

    public List<Campaign> findAllCampaigns();

    public List<Campaign> findCampaignByGroupId(final int groupId);

    public Campaign findCampaignById(int id);

    public void saveCampaign(Campaign campaignGroup);

    public void saveCampaignList(List<Campaign> campaignGroupList);

    public void deleteCampaignById(int groupId);

}
