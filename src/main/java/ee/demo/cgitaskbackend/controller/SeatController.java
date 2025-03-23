package ee.demo.cgitaskbackend.controller;

import ee.demo.cgitaskbackend.criteria.PreferredSeatCriteria;
import ee.demo.cgitaskbackend.dto.SeatDto;
import ee.demo.cgitaskbackend.service.SeatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/seat-schemas")
public class SeatController {

    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<SeatDto[][]> getSeatSchemasWithPreferred(
            @Valid @ModelAttribute PreferredSeatCriteria criteria,
            int airplaneId
    ) {
        return ResponseEntity.ok(seatService.getSeatSchemaWithRecommendations(airplaneId, criteria));
    }
}
