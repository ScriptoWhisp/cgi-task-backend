package ee.demo.cgitaskbackend.repository;

import ee.demo.cgitaskbackend.domain.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Integer>, JpaSpecificationExecutor<FlightEntity> {
}
