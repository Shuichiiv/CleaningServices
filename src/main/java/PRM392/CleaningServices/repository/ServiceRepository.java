package PRM392.CleaningServices.repository;

import PRM392.CleaningServices.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
