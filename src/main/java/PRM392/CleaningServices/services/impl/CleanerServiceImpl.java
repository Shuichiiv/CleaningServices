package PRM392.CleaningServices.services.impl;

import PRM392.CleaningServices.model.Schedule;
import PRM392.CleaningServices.model.Booking;
import PRM392.CleaningServices.repository.ScheduleRepository;
import PRM392.CleaningServices.repository.BookingRepository;
import PRM392.CleaningServices.services.CleanerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CleanerServiceImpl implements CleanerServices {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Schedule> getCleanerSchedule(Long cleanerId) {
        // Fetch the schedule for the cleaner based on their ID
        List<Schedule> schedules = scheduleRepository.findByCleaner_UserId(cleanerId);
        if (schedules.isEmpty()) {
            // Optionally handle case if no schedules are found
            System.out.println("No schedules found for cleaner with ID: " + cleanerId);
        }
        return schedules;
    }

    @Override
    public String getJobNotifications(Long cleanerId) {
        // Logic to get job notifications.
        // Fetch schedules and generate a notification message if there are new jobs.
        List<Schedule> schedules = scheduleRepository.findByCleaner_UserId(cleanerId);
        if (schedules.isEmpty()) {
            return "No new jobs at the moment.";
        }

        StringBuilder notificationMessage = new StringBuilder("New jobs for you:\n");
        schedules.forEach(schedule -> {
            notificationMessage.append("Job on ")
                    .append(schedule.getScheduledDate())
                    .append(" from ")
                    .append(schedule.getStartTime())
                    .append(" to ")
                    .append(schedule.getEndTime())
                    .append(".\n");
        });

        return notificationMessage.toString();
    }

    @Override
    public String confirmCompletion(Long jobId, Long cleanerId) {
        // Confirm job completion by updating the job status in Booking
        Optional<Booking> optionalBooking = bookingRepository.findById(jobId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            // Check if the booking belongs to the cleaner trying to confirm completion
            if (booking.getCleaner() != null && booking.getCleaner().getUserId().equals(cleanerId)) {
                // Ensure the booking is not already completed
                if (!booking.isCompleted()) {
                    // Mark the job as completed
                    booking.markAsCompleted();
                    bookingRepository.save(booking);  // Persist the changes to the database

                    // Optionally, handle customer feedback after job completion
                    String feedbackMessage = handleCustomerFeedback(booking);

                    return "Job completed successfully. " + feedbackMessage;
                } else {
                    return "Job has already been completed.";
                }
            } else {
                return "You are not authorized to complete this job.";
            }
        } else {
            return "Job not found.";
        }
    }

    /**
     * Handles customer feedback after job completion.
     * In this placeholder method, we assume customer feedback is optional but can be handled if available.
     */
    private String handleCustomerFeedback(Booking booking) {
        // Example feedback logic. In a real scenario, this might involve asking customers for feedback.
        String customerFeedback = "Thanks for your hard work!";  // This can be fetched from a feedback system.

        // Optional: save feedback to booking record if needed
        // booking.setCustomerFeedback(customerFeedback); // Uncomment if there's a feedback field in Booking
        // bookingRepository.save(booking);  // Uncomment if feedback needs to be persisted

        return "Customer feedback received: " + customerFeedback;
    }
}
