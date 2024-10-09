package PRM392.CleaningServices.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String profileImage;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}