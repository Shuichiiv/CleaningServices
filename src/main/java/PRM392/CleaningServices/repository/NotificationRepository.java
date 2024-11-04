package PRM392.CleaningServices.repository;

import PRM392.CleaningServices.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser_UserId(Long userId); // Fetch notifications by user ID
}
