package PRM392.CleaningServices.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequest {
    private Long servicesId;
    private String serviceName;
    private String description;
    private Double price;
    private Integer duration; //Minute
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
