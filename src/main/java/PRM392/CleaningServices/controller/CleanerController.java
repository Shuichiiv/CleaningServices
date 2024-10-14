package PRM392.CleaningServices.controller;

import PRM392.CleaningServices.dto.request.JobCompletionRequest;
import PRM392.CleaningServices.model.Schedule;
import PRM392.CleaningServices.services.CleanerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cleaner")
public class CleanerController {

    @Autowired
    private CleanerServices cleanerService;

    // View cleaner's schedule
    @GetMapping("/schedule/{cleanerId}")
    public ResponseEntity<List<Schedule>> viewSchedule(@PathVariable Long cleanerId) {
        List<Schedule> scheduleList = cleanerService.getCleanerSchedule(cleanerId);
        return ResponseEntity.ok(scheduleList);
    }

    // Get job notifications
    @GetMapping("/notifications/{cleanerId}")
    public ResponseEntity<String> getJobNotifications(@PathVariable Long cleanerId) {
        String notification = cleanerService.getJobNotifications(cleanerId);
        return ResponseEntity.ok(notification);
    }

    // Confirm completion of a job
    @PostMapping("/confirmCompletion")
    public ResponseEntity<String> confirmCompletion(@RequestBody JobCompletionRequest request) {
        String result = cleanerService.confirmCompletion(request.getJobId(), request.getCleanerId());
        if (result.equals("Success")) {
            return ResponseEntity.ok("Job confirmed successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to confirm job.");
    }
}
