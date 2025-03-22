package ee.demo.cgitaskbackend.repository;

import ee.demo.cgitaskbackend.domain.AirplaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<AirplaneEntity, Integer> {
}
