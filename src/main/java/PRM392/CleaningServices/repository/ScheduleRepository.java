package PRM392.CleaningServices.repository;

import PRM392.CleaningServices.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByCleanerId(Long cleanerId);
}