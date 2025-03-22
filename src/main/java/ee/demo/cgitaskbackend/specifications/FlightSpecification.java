package ee.demo.cgitaskbackend.specifications;

import ee.demo.cgitaskbackend.domain.FlightEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class FlightSpecification {

    private FlightSpecification() {
    }

    public static Specification<FlightEntity> hasDestination(String destination) {
        return (root, query, cb) ->
                destination == null ? null :
                        cb.like(cb.lower(root.get("destination")), "%" + destination + "%");
    }

    public static Specification<FlightEntity> hasDeparture(String departure) {
        return (root, query, cb) ->
                departure == null ? null :
                        cb.like(cb.lower(root.get("departure")), "%" + departure + "%");
    }

    public static Specification<FlightEntity> hasDepartureTime(Instant departureTime) {
        return (root, query, cb) -> cb.equal(root.get("departureTime"), departureTime);
    }

    public static Specification<FlightEntity> priceInRange(Integer priceLow, Integer priceHigh) {
        return (root, query, cb) -> {
            if (priceLow == null && priceHigh == null) {
                return null;
            } else if (priceLow == null) {
                return cb.lessThanOrEqualTo(root.get("price"), priceHigh);
            } else if (priceHigh == null) {
                return cb.greaterThanOrEqualTo(root.get("price"), priceLow);
            } else {
                return cb.between(root.get("price"), priceLow, priceHigh);
            }
        };
    }

    public static Specification<FlightEntity> hasAirplaneId(Long airplaneId) {
        return (root, query, cb) -> cb.equal(root.get("airplane").get("id"), airplaneId);
    }
}
