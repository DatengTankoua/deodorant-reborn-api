package com.deodorantreborn.api.feedback.service;

import com.deodorantreborn.api.feedback.entity.Feedback;
import com.deodorantreborn.api.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback getFeedback() {
        // Implement the logic to retrieve feedback
        Feedback feedback = feedbackRepository.findAll().stream().filter(f -> f.getEmail() != null && f.getEmail().equals("researcher@uni.de")).findFirst().orElse(null);
        return feedback;
    }
    
}
