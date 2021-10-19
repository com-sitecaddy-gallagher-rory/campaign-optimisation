package rorygall.demo.campaignoptimisation.bootstrap;

import rorygall.demo.campaignoptimisation.bootstrap.helpers.CSVHelper;
import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.service.CampaignGroupService;
import rorygall.demo.campaignoptimisation.service.CampaignService;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CampaignService campaignService;
    private final CampaignGroupService campaignGroupService;

    public DataLoader(final CampaignService campaignService, final CampaignGroupService campaignGroupService) {
        this.campaignService = campaignService;
        this.campaignGroupService = campaignGroupService;
    }

    /**
     * On startup check If any campaign groups yet, if none then create 1 and import campaigns from csv file
     * @param args
     * @throws Exception
     */
    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Start Loading data!");

        final List<CampaignGroup> campaignGroups = campaignGroupService.findAllCampaignGroups();
        if (campaignGroups == null || campaignGroups.isEmpty()) {
            final CampaignGroup campaignGroup = new CampaignGroup();
            campaignGroup.setName("Group1");

            final List<Campaign> allCampaigns = campaignService.findAllCampaigns();
            if (allCampaigns == null || allCampaigns.isEmpty()) {

                final Resource resource = new ClassPathResource("campaigns.csv");
                final List<Campaign> campaignList = CSVHelper.buildGroupsFromCSV(resource.getInputStream(), campaignGroup);
                System.out.println("Finished Loading data!" + campaignList);
                campaignGroup.setCampaignList(campaignList);
            }
            campaignGroupService.saveCampaignGroup(campaignGroup);

        }
    }
}
