package ee.demo.cgitaskbackend.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Schema(hidden = true)
@Getter
@Setter
@Entity(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String destination;
    private String departure;
    @Column(name = "departure_time")
    private Instant departureTime;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private AirplaneEntity airplaneId;
}
