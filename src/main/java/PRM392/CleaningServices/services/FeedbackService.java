package PRM392.CleaningServices.services;

import PRM392.CleaningServices.model.Feedback;
import PRM392.CleaningServices.repository.FeedbackRepository;
import PRM392.CleaningServices.services.impl.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedbackDetails) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setRating(feedbackDetails.getRating());
        feedback.setComment(feedbackDetails.getComment());
        feedback.setCreatedDate(feedbackDetails.getCreatedDate());
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
