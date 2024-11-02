package PRM392.CleaningServices.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
    private Long feedbackId;
    private Long bookingId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdDate;
}
