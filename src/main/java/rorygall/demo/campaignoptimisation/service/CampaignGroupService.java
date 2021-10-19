package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.entity.CampaignGroup;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CampaignGroupService {

    public List<CampaignGroup> findAllCampaignGroups();

    public CampaignGroup findCampaignGroupById(final int groupId);

    public void saveCampaignGroup(CampaignGroup campaignGroup);

    public void saveCampaignGroupList(List<CampaignGroup> campaignGroupList);

    public void deleteCampaignGroupById(int groupId);

}
