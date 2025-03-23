package ee.demo.cgitaskbackend.repository;

import ee.demo.cgitaskbackend.domain.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Integer> {
    List<SeatEntity> findAllByAirplane_Id(int id);
}
