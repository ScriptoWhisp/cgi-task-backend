package ee.demo.cgitaskbackend.criteria;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record PreferredSeatCriteria(

    @PositiveOrZero
    Integer price,
    Boolean extraLegroom,
    Boolean nearWindow,
    Boolean nearExit,
    @Positive
    Integer seatsCount
) {}
