package com.deodorantreborn.api.featurerequest.service;

import com.deodorantreborn.api.featurerequest.dto.request.FeatureRequestRequest;
import com.deodorantreborn.api.featurerequest.dto.response.FeatureRequestResponse;
import com.deodorantreborn.api.featurerequest.entity.FeatureRequest;
import com.deodorantreborn.api.featurerequest.repository.FeatureRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeatureRequestService {

    private final FeatureRequestRepository featureRequestRepository;

    // Alle FeatureRequests holen
    public List<FeatureRequestResponse> getAllFeatureRequest() {
        return featureRequestRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Ein FeatureRequest holen
    public FeatureRequestResponse getFeatureRequestById(UUID id) {
        FeatureRequest feedback = featureRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
        return toResponse(feedback);
    }

    // Neues FeatureRequest speichern
    public FeatureRequestResponse submitFeatureRequest(FeatureRequestRequest request) {
        FeatureRequest feedback = FeatureRequest.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .email(request.getEmail())
                .build();

        FeatureRequest saved = featureRequestRepository.save(feedback);
        return toResponse(saved);
    }

    // FeatureRequest löschen
    public void deleteFeatureRequest(UUID id) {
        featureRequestRepository.deleteById(id);
    }

    // Entity → Response DTO umwandeln
    private FeatureRequestResponse toResponse(FeatureRequest featureRequest) {
        return FeatureRequestResponse.builder()
                .id(featureRequest.getId())
                .title(featureRequest.getTitle())
                .description(featureRequest.getDescription())
                .email(featureRequest.getEmail())
                .createdAt(featureRequest.getCreatedAt())
                .build();
    }
    
}
