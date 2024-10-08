package PRM392.CleaningServices.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking; // Relationship to Booking

    private String paymentMethod;
    private Double amountPaid;
    private String paymentStatus; // E.g. Completed, Pending
    private LocalDateTime paymentDate;
}
