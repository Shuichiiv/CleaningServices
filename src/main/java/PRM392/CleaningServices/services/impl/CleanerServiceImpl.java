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
    public String confirmCompletion(Long bookingId, Long cleanerId) {


        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);


        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();


            if (booking.getCleaner() != null && booking.getCleaner().getUserId().equals(cleanerId)) {


                if (!booking.isCompleted()) {

                    booking.markAsCompleted();
                    bookingRepository.save(booking);


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



    private String handleCustomerFeedback(Booking booking) {

        String customerFeedback = "Thanks for your hard work!";



        return "Customer feedback received: " + customerFeedback;
    }
}
