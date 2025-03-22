package ee.demo.cgitaskbackend.service;

import ee.demo.cgitaskbackend.criteria.FlightSearchCriteria;
import ee.demo.cgitaskbackend.domain.FlightEntity;
import ee.demo.cgitaskbackend.dto.FlightDto;
import ee.demo.cgitaskbackend.mapstruct.FlightMapper;
import ee.demo.cgitaskbackend.repository.FlightRepository;
import ee.demo.cgitaskbackend.specifications.FlightSpecification;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public Page<FlightDto> getFlights(FlightSearchCriteria criteria, @Min(0) int pageNo, @Min(1) int pageSize) {
        Specification<FlightEntity> spec = Specification.where(null);

        if (criteria.destination() != null) {
            spec = spec.and(FlightSpecification.hasDestination(criteria.destination()));
        }

        if (criteria.departure() != null) {
            spec = spec.and(FlightSpecification.hasDeparture(criteria.departure()));
        }

        if (criteria.departureTime() != null) {
            spec = spec.and(FlightSpecification.hasDepartureTime(criteria.departureTime()));
        }

        if (criteria.priceLow() != null || criteria.priceHigh() != null) {
            spec = spec.and(FlightSpecification.priceInRange(criteria.priceLow(), criteria.priceHigh()));
        }

        return flightRepository.findAll(spec, PageRequest.of(pageNo, pageSize)).map(flightMapper::toDto);
    }
}
