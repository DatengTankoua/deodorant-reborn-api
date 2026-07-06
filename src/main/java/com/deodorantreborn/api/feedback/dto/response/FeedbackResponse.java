package com.deodorantreborn.api.feedback.dto.response;

import java.lang.annotation.Native;
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
public class FeedbackResponse {

    @NotNull
    private UUID id;

    @NotNull @NotBlank
    private String email;

    @NotNull @NotBlank
    private String message;

    @NotNull
    private Integer rating;

    @NotNull
    private OffsetDateTime createdAt;
}
