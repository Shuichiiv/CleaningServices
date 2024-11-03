package PRM392.CleaningServices.controller;

import PRM392.CleaningServices.dto.request.FeedbackDTO;
import PRM392.CleaningServices.model.Feedback;
import PRM392.CleaningServices.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create")
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Feedback feedback = convertToEntity(feedbackDTO);
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return convertToDTO(createdFeedback);
    }

    @GetMapping("/getall")
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        try {
            Feedback feedbackDetails = convertToEntity(feedbackDTO);
            Feedback updatedFeedback = feedbackService.updateFeedback(id, feedbackDetails);
            return ResponseEntity.ok(convertToDTO(updatedFeedback));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    private FeedbackDTO convertToDTO(Feedback feedback) {
        return new FeedbackDTO(
                feedback.getFeedbackId(),
                feedback.getBooking().getBookingId(),
                feedback.getRating(),
                feedback.getComment(),
                feedback.getCreatedDate()
        );
    }

    private Feedback convertToEntity(FeedbackDTO feedbackDTO) {
        Feedback feedback = Feedback.builder()
                .feedbackId(feedbackDTO.getFeedbackId())
                .rating(feedbackDTO.getRating())
                .comment(feedbackDTO.getComment())
                .createdDate(feedbackDTO.getCreatedDate())
                .build();

        return feedback;
    }
}