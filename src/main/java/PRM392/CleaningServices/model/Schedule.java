package PRM392.CleaningServices.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "cleanerId", nullable = false)
    private User cleaner; // Relationship to User (Staff-Cleaner)

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking; // Relationship to Booking

    private LocalDateTime scheduledDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
