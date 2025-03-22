package ee.demo.cgitaskbackend.criteria;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;

@Builder
public record FlightSearchCriteria(

    @PositiveOrZero
    Long id,
    @Size(max = 255)
    String destination,
    @Size(max = 255)
    String departure,
    Instant departureTime,
    @PositiveOrZero
    Integer priceHigh,
    @PositiveOrZero
    Integer priceLow

) {}

