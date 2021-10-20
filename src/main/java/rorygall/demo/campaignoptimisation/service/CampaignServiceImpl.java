package rorygall.demo.campaignoptimisation.service;

import rorygall.demo.campaignoptimisation.dao.CampaignDao;
import rorygall.demo.campaignoptimisation.dao.CampaignGroupDao;
import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;


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
        CampaignGroup campaignGroup = campaignGroupDao.findCampaignGroupById(groupId)
                .orElseThrow(() -> new OptimisationException("No Group Id:" + groupId));
        return campaignGroup.getCampaignList();
    }

    @Override
    @Transactional
    public Campaign findCampaignById(final int id) {
        return campaignDao.findCampaignById(id)
                .orElseThrow(() -> new OptimisationException("Campaign not found for Id:"+id));
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
