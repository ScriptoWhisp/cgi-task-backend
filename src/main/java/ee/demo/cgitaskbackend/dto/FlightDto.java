package ee.demo.cgitaskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightDto {

    private Integer id;
    private String destination;
    private String departure;
    private String departureTime;
    private Integer price;
    private Integer airplaneId;
}
