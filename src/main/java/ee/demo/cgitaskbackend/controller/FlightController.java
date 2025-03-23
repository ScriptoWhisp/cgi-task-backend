package ee.demo.cgitaskbackend.controller;

import ee.demo.cgitaskbackend.criteria.FlightSearchCriteria;
import ee.demo.cgitaskbackend.dto.FlightDto;
import ee.demo.cgitaskbackend.service.FlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<Page<FlightDto>> getProducts(
            @Valid @ModelAttribute FlightSearchCriteria criteria,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {
        System.out.println("criteria = " + criteria);
        return ResponseEntity.ok(flightService.getFlights(criteria, pageNo, pageSize));
    }
}
