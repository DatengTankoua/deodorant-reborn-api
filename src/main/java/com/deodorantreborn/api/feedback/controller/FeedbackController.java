package com.deodorantreborn.api.feedback.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.deodorantreborn.api.feedback.service.FeedbackService;
import com.deodorantreborn.api.feedback.entity.Feedback;
import com.deodorantreborn.api.feedback.dto.response.FeedbackResponse; 

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public FeedbackResponse getFeedback() {
        Feedback feedback = feedbackService.getFeedback();
        if (feedback == null) {
            return null; // or handle the case when no feedback is found
        }
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .email(feedback.getEmail())
                .message(feedback.getMessage())
                .rating(feedback.getRating())
                .createdAt(feedback.getCreatedAt())
                .build();
    }
    
}
