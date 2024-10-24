package PRM392.CleaningServices.dto.request;


import lombok.Data;

@Data
public class JobCompletionRequest {
    private Long bookingId;
    private Long cleanerId;
}