package PRM392.CleaningServices.controller;

import PRM392.CleaningServices.model.Schedule;
import PRM392.CleaningServices.services.CleanerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cleaner")
public class CleanerController {

    @Autowired
    private CleanerServices cleanerService;

    @GetMapping("/schedule/{cleanerId}")
    public ResponseEntity<List<Schedule>> viewSchedule(@PathVariable Long cleanerId) {
        List<Schedule> scheduleList = cleanerService.getCleanerSchedule(cleanerId);
        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping("/notifications/{cleanerId}")
    public ResponseEntity<String> getJobNotifications(@PathVariable Long cleanerId) {
        String notification = cleanerService.getJobNotifications(cleanerId);
        return ResponseEntity.ok(notification);
    }

    @PostMapping("/confirmCompletion")
    public ResponseEntity<String> confirmCompletion(
            @RequestParam Long jobId,
            @RequestParam Long cleanerId) {
        String result = cleanerService.confirmCompletion(jobId, cleanerId);
        return ResponseEntity.ok(result);
    }
}
