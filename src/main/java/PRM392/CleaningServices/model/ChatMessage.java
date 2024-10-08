package PRM392.CleaningServices.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    private User sender; // Relationship to User

    @ManyToOne
    @JoinColumn(name = "receiverId", nullable = false)
    private User receiver; // Relationship to User

    private String content;
    private LocalDateTime sendDate;
}
