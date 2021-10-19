package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.entity.Optimisation;
import rorygall.demo.campaignoptimisation.resource.OptimisationErrorResponse;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;
import rorygall.demo.campaignoptimisation.service.CampaignGroupService;
import rorygall.demo.campaignoptimisation.service.OptimisationService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OptimisationController {

    private OptimisationService optimisationService;
    private CampaignGroupService campaignGroupService;

    public OptimisationController(final OptimisationService optimisationService, final CampaignGroupService campaignGroupService) {
        this.optimisationService = optimisationService;
        this.campaignGroupService = campaignGroupService;
    }

    @GetMapping("/optimisation/view/{groupId}")
    public List<Optimisation> viewOptimisation(@PathVariable("groupId") int groupId) {
        List<Optimisation> optimisations = Optional.ofNullable(campaignGroupService.findCampaignGroupById(groupId).getOptimisationList())
                .orElse(Collections.emptyList());
        return optimisations.stream().filter(o -> o.getStatus().equals("proposed")).collect(Collectors.toList());
    }

    @GetMapping("/optimisation/create/{groupId}")
    public Optimisation createOptimisation(@PathVariable("groupId") int groupId) {
        return optimisationService.createOptimisationsForGroup(groupId);
    }

    @PostMapping("/optimisation/save/{groupId}")
    public void saveOptimisation(@PathVariable("groupId") int groupId) {
        optimisationService.createAndSaveOptimisationsForGroup(groupId);
    }

    @PutMapping("/optimisation/apply/{optimisationId}")
    public void applyOptimisation(@PathVariable("optimisationId") int optimisationId) {
        optimisationService.applyOptimisationForGroup(optimisationId);
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
