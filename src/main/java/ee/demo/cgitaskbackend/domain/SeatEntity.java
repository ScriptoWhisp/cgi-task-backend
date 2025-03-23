package ee.demo.cgitaskbackend.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Schema(hidden = true)
@Getter
@Setter
@Entity(name = "seats")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "seat_row")
    private Integer row;
    @Column(name = "seat_column")
    private Integer column;
    private Integer price;
    @Column(name = "is_booked")
    private Boolean isBooked;
    @Column(name = "extra_legroom")
    private Boolean extraLegroom;


    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private AirplaneEntity airplane;
}
