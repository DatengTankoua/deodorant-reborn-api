package com.deodorantreborn.api.featurerequest.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureRequestResponse {

    @NotNull
    private UUID id;

    @NotNull @NotBlank
    private String title;

    @NotNull @NotBlank
    private String description;

    @NotNull @NotBlank
    private String email;

    @NotNull
    private Integer votes;

    @NotNull
    private OffsetDateTime createdAt;
    
}
