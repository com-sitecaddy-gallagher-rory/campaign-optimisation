package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.dao.CampaignDao;
import rorygall.demo.campaignoptimisation.dao.CampaignGroupDao;
import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;


import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CampaignService{

    private CampaignDao campaignDao;
    private CampaignGroupDao campaignGroupDao;
    public CampaignServiceImpl(CampaignDao campaignDao, CampaignGroupDao campaignGroupDao) {
        this.campaignDao = campaignDao;
        this.campaignGroupDao = campaignGroupDao;
    }

    @Override
    @Transactional
    public List<Campaign> findAllCampaigns() {
        return campaignDao.getAllCampaigns();
    }

    @Override
    @Transactional
    public List<Campaign> findCampaignByGroupId(final int groupId) {
        CampaignGroup campaignGroup = campaignGroupDao.findCampaignGroupById(groupId);
        if(campaignGroup != null && campaignGroup.getCampaignList() != null)
        {
            return campaignGroup.getCampaignList();
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Campaign findCampaignById(final int groupId) {
        return campaignDao.findCampaignById(groupId);
    }

    @Override
    @Transactional
    public void saveCampaign(final Campaign campaign) {
        campaignDao.saveCampaign(campaign);
    }

    @Override
    @Transactional
    public void saveCampaignList(final List<Campaign> campaignList) {
        if(campaignList != null) {
            campaignList.stream().forEach(cg -> saveCampaign(cg));
        }

    }

    @Override
    @Transactional
    public void deleteCampaignById(final int groupId) {

    }
}
