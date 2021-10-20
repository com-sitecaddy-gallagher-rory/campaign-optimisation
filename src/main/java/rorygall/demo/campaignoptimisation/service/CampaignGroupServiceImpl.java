package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.dao.CampaignGroupDao;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CampaignGroupServiceImpl implements CampaignGroupService{

    private CampaignGroupDao campaignGroupDao;
    public CampaignGroupServiceImpl(CampaignGroupDao campaignGroupDao) {
        this.campaignGroupDao = campaignGroupDao;
    }

    @Override
    public List<CampaignGroup> findAllCampaignGroups() {
        return campaignGroupDao.findAllCampaignGroups();
    }

    @Override
    public CampaignGroup findCampaignGroupById(final int groupId) {
        return campaignGroupDao.findCampaignGroupById(groupId)
                .orElseThrow(() -> new OptimisationException("Campaign Group not found for Id:"+groupId));
    }

    @Override
    public void saveCampaignGroup(final CampaignGroup campaignGroup) {
        campaignGroupDao.saveCampaignGroup(campaignGroup);
    }

    @Override
    public void saveCampaignGroupList(final List<CampaignGroup> campaignGroupList) {
        if(campaignGroupList != null) {
            campaignGroupList.stream().forEach(group -> saveCampaignGroup(group));
        }
    }

    @Override
    public void deleteCampaignGroupById(final int groupId) {

    }
}
