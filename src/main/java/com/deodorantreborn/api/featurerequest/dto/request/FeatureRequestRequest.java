package com.deodorantreborn.api.featurerequest.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FeatureRequestRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 05 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @Email(message = "Email must be valid")
    private String email;
    
}
