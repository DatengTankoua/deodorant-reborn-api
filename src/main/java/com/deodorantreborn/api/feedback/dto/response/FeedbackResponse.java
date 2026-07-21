package com.deodorantreborn.api.feedback.dto.response;

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
public class FeedbackResponse {

    private UUID id;

    private String email;

    private String message;

    private Integer rating;

    private OffsetDateTime createdAt;
}
