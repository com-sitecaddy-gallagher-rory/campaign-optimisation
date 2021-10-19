package rorygall.demo.campaignoptimisation.controller;

import rorygall.demo.campaignoptimisation.entity.Recommendation;
import rorygall.demo.campaignoptimisation.resource.OptimisationErrorResponse;
import rorygall.demo.campaignoptimisation.resource.OptimisationException;
import rorygall.demo.campaignoptimisation.service.RecommendationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommendation/{optimisationId}")
    public List<Recommendation> getOptimisation(@PathVariable("optimisationId") int optimisationId) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsForOptimisation(optimisationId);
        System.out.println("Recommendations: " + recommendations);
        return recommendations;
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
