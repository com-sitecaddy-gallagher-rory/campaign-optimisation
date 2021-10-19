package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.entity.CampaignGroup;
import rorygall.demo.campaignoptimisation.resource.OptimisationErrorResponse;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;
import rorygall.demo.campaignoptimisation.service.CampaignGroupService;

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
public class CampaignGroupController {

    // Our service layer object
    private CampaignGroupService campaignGroupService;

    @Autowired
    public CampaignGroupController(CampaignGroupService theCampaignGroupService)
    {
        campaignGroupService = theCampaignGroupService;
    }

    @GetMapping("/group")
    public List<CampaignGroup> getAllCampaignGroups() {
        List<CampaignGroup> groups = campaignGroupService.findAllCampaignGroups();
        return groups;
    }

    @GetMapping("/group/{groupId}")
    public CampaignGroup getCampaignGroup(@PathVariable("groupId") int groupId) throws Exception {
        return campaignGroupService.findCampaignGroupById(groupId);
    }

    @PostMapping("/group")
    public CampaignGroup createGroup(@RequestBody CampaignGroup campaignGroup) throws Exception {
        campaignGroup.setId(0);

        campaignGroupService.saveCampaignGroup(campaignGroup);

        return campaignGroup;
    }

    @ExceptionHandler
    public ResponseEntity<OptimisationErrorResponse> handleException(final OptimisationException exception) {
        OptimisationErrorResponse error = new OptimisationErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
