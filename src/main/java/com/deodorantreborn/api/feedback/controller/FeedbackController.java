package com.deodorantreborn.api.feedback.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;  

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @GetMapping
    public String getFeedback() {
        // Implement the logic to retrieve feedback
        return "Feedback retrieved successfully";
    }
    
}
