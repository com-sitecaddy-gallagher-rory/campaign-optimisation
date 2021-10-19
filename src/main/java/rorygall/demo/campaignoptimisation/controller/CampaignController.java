package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.entity.Campaign;
import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.resource.OptimisationErrorResponse;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;
import rorygall.demo.campaignoptimisation.service.CampaignGroupService;
import rorygall.demo.campaignoptimisation.service.CampaignService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CampaignController {

    // Our service layer object
    private CampaignService campaignService;
    private CampaignGroupService campaignGroupService;

    @Autowired
    public CampaignController(final CampaignService theCampaignService, final CampaignGroupService theCampaignGroupService) {
        campaignService = theCampaignService;
        campaignGroupService = theCampaignGroupService;
    }

    @GetMapping("/campaign")
    public List<Campaign> getAllCampaigns() {
        return campaignService.findAllCampaigns();
    }

    @GetMapping("/campaign/{id}")
    public Campaign getCampaign(@PathVariable("id") int id) {
        return campaignService.findCampaignById(id);
    }

    @GetMapping("/campaign/group/{groupId}")
    public List<Campaign> getAllCampaignsForGroup(@PathVariable("groupId") int groupId) {
        return campaignService.findCampaignByGroupId(groupId);
    }

    @PostMapping("/campaign/{groupId}")
    public Campaign createGroup(@RequestBody Campaign campaign, @PathVariable("groupId") int groupId) throws Exception {
        campaign.setId(0);
        CampaignGroup campaignGroup = campaignGroupService.findCampaignGroupById(groupId);
        campaign.setGroup(campaignGroup);
        campaignService.saveCampaign(campaign);

        return campaign;
    }

    @ExceptionHandler
    public ResponseEntity<OptimisationErrorResponse> handleException(final OptimisationException exception) {
        OptimisationErrorResponse error = new OptimisationErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * <p>Exception Handler to return a ResponseEntity to the caller for General Exceptions </p>
     * <p>This will contain basic error information which can be passed back to the caller.</p>
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<OptimisationErrorResponse> handleException(Exception ex)
    {
        OptimisationErrorResponse error = new OptimisationErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
