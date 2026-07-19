package com.deodorantreborn.api.feedback.service;

import com.deodorantreborn.api.feedback.dto.request.FeedbackRequest;
import com.deodorantreborn.api.feedback.dto.response.FeedbackResponse;
import com.deodorantreborn.api.feedback.entity.Feedback;
import com.deodorantreborn.api.feedback.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    // Alle Feedbacks holen
    public List<FeedbackResponse> getAllFeedback() {
        return feedbackRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Ein Feedback holen
    public FeedbackResponse getFeedbackById(UUID id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id: " + id));
        return toResponse(feedback);
    }

    // Neues Feedback speichern
    public FeedbackResponse submitFeedback(FeedbackRequest request) {
        Feedback feedback = Feedback.builder()
                .email(request.getEmail())
                .message(request.getMessage())
                .rating(request.getRating())
                .build();

        Feedback saved = feedbackRepository.save(feedback);
        return toResponse(saved);
    }

    // Feedback löschen
    public void deleteFeedback(UUID id) {
        feedbackRepository.deleteById(id);
    }

    // Entity → Response DTO umwandeln
    private FeedbackResponse toResponse(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .email(feedback.getEmail())
                .message(feedback.getMessage())
                .rating(feedback.getRating())
                .createdAt(feedback.getCreatedAt())
                .build();
    }
}