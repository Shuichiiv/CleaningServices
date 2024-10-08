package PRM392.CleaningServices.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "bookingId", nullable = false)
    private Booking booking; // Relationship to Booking

    @ManyToOne
    @JoinColumn(name = "serviceId", nullable = false)
    private Service service; // Relationship to Service

    private Double price;
    private Integer quantity;
    private Double subTotal;
}
