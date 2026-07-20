package com.deodorantreborn.api.featurerequest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deodorantreborn.api.featurerequest.dto.request.FeatureRequestRequest;
import com.deodorantreborn.api.featurerequest.dto.response.FeatureRequestResponse;
import com.deodorantreborn.api.featurerequest.service.FeatureRequestService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feature-requests")
@RequiredArgsConstructor
public class FeatureRequestController {

    private final FeatureRequestService featureRequestService;

    /**
     * GET /api/feature-requests
     * Gibt alle FeatureRequests zurück
     */
    @GetMapping
    public ResponseEntity<List<FeatureRequestResponse>> getAllFeatureRequest() {
        List<FeatureRequestResponse> featureRequests = featureRequestService.getAllFeatureRequest();
        return ResponseEntity.ok(featureRequests);
    }

    /**
     * GET /api/feature-requests/{id}
     * Gibt ein einzelnes FeatureRequest zurück
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeatureRequestResponse> getFeatureRequestById(@PathVariable UUID id) {
        FeatureRequestResponse featureRequest = featureRequestService.getFeatureRequestById(id);
        return ResponseEntity.ok(featureRequest);
    }

    /**
     * POST /api/feature-requests
     * Erstellt ein neues FeatureRequest
     */
    @PostMapping
    public ResponseEntity<FeatureRequestResponse> submitFeatureRequest(
            @Valid @RequestBody FeatureRequestRequest request) {
        FeatureRequestResponse created = featureRequestService.submitFeatureRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * DELETE /api/feature-requests/{id}
     * Löscht ein FeatureRequest
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable UUID id) {
        featureRequestService.deleteFeatureRequest(id);
        return ResponseEntity.noContent().build();
    }
    
}
