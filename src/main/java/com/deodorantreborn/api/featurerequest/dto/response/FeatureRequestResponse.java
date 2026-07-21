package com.deodorantreborn.api.featurerequest.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureRequestResponse {

    private UUID id;

    private String title;

    private String description;

    private String email;

    private Integer votes;

    private OffsetDateTime createdAt;
    
}
