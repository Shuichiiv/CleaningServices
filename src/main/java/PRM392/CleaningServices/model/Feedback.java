package PRM392.CleaningServices.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking; // Relationship to Booking

    private Integer rating; // Rating out of 5
    private String comment;
    private LocalDateTime createdDate;
}
