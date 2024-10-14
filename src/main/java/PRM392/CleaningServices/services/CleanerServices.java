package PRM392.CleaningServices.services;

import PRM392.CleaningServices.model.Schedule;

import java.util.List;

public interface CleanerServices {
    List<Schedule> getCleanerSchedule(Long cleanerId);
    String getJobNotifications(Long cleanerId);
    String confirmCompletion(Long jobId, Long cleanerId);
}
