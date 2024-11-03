package PRM392.CleaningServices.repository;


import PRM392.CleaningServices.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByCleaner_UserId(Long cleanerId);
}
