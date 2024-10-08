package PRM392.CleaningServices.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user; // Relationship to User

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private LocalDateTime createdDate;

}
