package ee.demo.cgitaskbackend.criteria;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Builder
public record FlightSearchCriteria(

    @PositiveOrZero
    Long id,
    @Size(max = 255)
    String destination,
    @Size(max = 255)
    String departure,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate departureDate,
    @PositiveOrZero
    Integer priceHigh,
    @PositiveOrZero
    Integer priceLow

) {}

