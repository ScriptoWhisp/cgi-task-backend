package ee.demo.cgitaskbackend.repository;

import ee.demo.cgitaskbackend.domain.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {
}
