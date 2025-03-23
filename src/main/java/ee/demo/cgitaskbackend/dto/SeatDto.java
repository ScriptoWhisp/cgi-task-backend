package ee.demo.cgitaskbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatDto {

    private Integer id;
    private Integer row;
    private Integer column;
    private Boolean isBooked;
    private Integer price;
    private Boolean extraLegroom;

}
