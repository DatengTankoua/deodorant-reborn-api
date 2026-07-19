package com.deodorantreborn.api.feedback.controller;

import com.deodorantreborn.api.feedback.dto.request.FeedbackRequest;
import com.deodorantreborn.api.feedback.dto.response.FeedbackResponse;
import com.deodorantreborn.api.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor          
public class FeedbackController {

    private final FeedbackService feedbackService;

    /**
     * GET /api/feedback
     * Gibt alle Feedbacks zurück
     */
    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllFeedback() {
        List<FeedbackResponse> feedbacks = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbacks);
    }

    /**
     * GET /api/feedback/{id}
     * Gibt ein einzelnes Feedback zurück
     */
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> getFeedbackById(@PathVariable UUID id) {
        FeedbackResponse feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    /**
     * POST /api/feedback
     * Erstellt ein neues Feedback
     */
    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(
            @Valid @RequestBody FeedbackRequest request) {
        FeedbackResponse created = feedbackService.submitFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * DELETE /api/feedback/{id}
     * Löscht ein Feedback (nur Admin)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable UUID id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}