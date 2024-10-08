package PRM392.CleaningServices.model;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private User customer; // Relationship to User (Customer)

    @ManyToOne
    @JoinColumn(name = "serviceId", nullable = false)
    private Service service; // Relationship to Service

    @ManyToOne
    @JoinColumn(name = "addressId", nullable = false)
    private Address address; // Relationship to Address

    private String status; // 'Pending', 'Assigned', 'In Progress', 'Completed', 'Cancelled'
    private Double totalPrice;
    private LocalDateTime createdDate;
}