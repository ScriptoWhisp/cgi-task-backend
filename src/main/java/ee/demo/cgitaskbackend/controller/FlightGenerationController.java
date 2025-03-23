package ee.demo.cgitaskbackend.controller;

import ee.demo.cgitaskbackend.service.FlightGenerationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/generate")
public class FlightGenerationController {

    private final FlightGenerationService flightGenerationService;

    @PostMapping("/flights")
    public ResponseEntity<String> generateFlights(@RequestParam(defaultValue = "1") int count) {
        flightGenerationService.generateRandomFlights(count);
        System.out.println("Рейсы успешно сгенерированы");
        return ResponseEntity.ok("Рейсы успешно сгенерированы");
    }
}
