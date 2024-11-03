package PRM392.CleaningServices.services.impl;

import PRM392.CleaningServices.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    Feedback createFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacks();
    Optional<Feedback> getFeedbackById(Long id);
    Feedback updateFeedback(Long id, Feedback feedbackDetails);
    void deleteFeedback(Long id);
}
