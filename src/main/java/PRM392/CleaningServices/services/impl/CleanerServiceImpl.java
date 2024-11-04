package PRM392.CleaningServices.services.impl;

import PRM392.CleaningServices.model.Booking;
import PRM392.CleaningServices.model.Notification;
import PRM392.CleaningServices.model.Schedule;
import PRM392.CleaningServices.repository.BookingRepository;
import PRM392.CleaningServices.repository.NotificationRepository;
import PRM392.CleaningServices.repository.ScheduleRepository;
import PRM392.CleaningServices.services.CleanerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CleanerServiceImpl implements CleanerServices {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private NotificationRepository notificationRepository; // New repository for notifications

    @Override
    public List<Schedule> getCleanerSchedule(Long cleanerId) {
        List<Schedule> schedules = scheduleRepository.findByCleaner_UserId(cleanerId);
        System.out.println("Cleaner ID: " + cleanerId + ", Schedules found: " + schedules.size());
        if (schedules.isEmpty()) {
            System.out.println("No schedules found for cleaner with ID: " + cleanerId);
        }
        return schedules;
    }

    @Override
    public String getJobNotifications(Long cleanerId) {
        List<Notification> notifications = notificationRepository.findByUser_UserId(cleanerId);
        System.out.println("Cleaner ID: " + cleanerId + ", Notifications count: " + notifications.size());
        
        if (notifications.isEmpty()) {
            return "No new job notifications.";
        }

        StringBuilder notificationMessage = new StringBuilder("You have new job notifications:\n");
        notifications.forEach(notification -> {
            notificationMessage.append("- ")
                    .append(notification.getMessage())
                    .append(" (")
                    .append(notification.getCreatedDate())
                    .append(")\n");
            // Mark notification as read
            notification.setIsRead(true);
            notificationRepository.save(notification);
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
                    
                    // Create a notification for job completion
                    createNotificationForJobCompletion(booking);
                    
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

    private void createNotificationForJobCompletion(Booking booking) {
        Notification notification = Notification.builder()
                .user(booking.getCleaner())
                .message("Job #" + booking.getBookingId() + " has been completed.")
                .isRead(false)
                .createdDate(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
        System.out.println("Notification created for job completion: " + notification.getMessage());
    }

    private String handleCustomerFeedback(Booking booking) {
        String customerFeedback = "Thanks for your hard work!";
        return "Customer feedback received: " + customerFeedback;
    }
}
