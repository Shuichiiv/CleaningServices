package PRM392.CleaningServices.services.impl;

import PRM392.CleaningServices.model.Booking;
import PRM392.CleaningServices.repository.BookingRepository;
import PRM392.CleaningServices.services.CleanerServices;
import PRM392.CleaningServices.model.Schedule;
import PRM392.CleaningServices.repository.ScheduleRepository;
import PRM392.CleaningServices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CleanerService implements CleanerServices {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Schedule> getCleanerSchedule(Long cleanerId) {
        return scheduleRepository.findByCleanerId(cleanerId);
    }


    public String getJobNotifications(Long cleanerId) {
        List<Schedule> schedules = scheduleRepository.findByCleanerId(cleanerId);
        if (schedules.isEmpty()) {
            return "No new job notifications.";
        }
        int newJobs = schedules.size();

        return "You have " + newJobs + " new job notifications.";
    }

    public String confirmCompletion(Long jobId, Long cleanerId) {

        Optional<Booking> bookingOptional = bookingRepository.findById(jobId);

        if (!bookingOptional.isPresent()) {
            return "Job with ID " + jobId + " not found.";
        }

        Booking booking = bookingOptional.get();
        if (booking.getCleaner() == null || !booking.getCleaner().getUserId().equals(cleanerId)) {
            return "Cleaner is not assigned to this job.";
        }

        // Mark the booking as complete
        booking.markAsCompleted();
        bookingRepository.save(booking);

        return "Job " + jobId + " completed by cleaner " + cleanerId + ". Customer rating: 5 stars.";
    }
}