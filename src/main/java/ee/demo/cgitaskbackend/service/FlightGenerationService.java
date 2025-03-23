package ee.demo.cgitaskbackend.service;

import ee.demo.cgitaskbackend.domain.AirplaneEntity;
import ee.demo.cgitaskbackend.domain.FlightEntity;
import ee.demo.cgitaskbackend.domain.SeatEntity;
import ee.demo.cgitaskbackend.repository.AirplaneRepository;
import ee.demo.cgitaskbackend.repository.FlightRepository;
import ee.demo.cgitaskbackend.repository.SeatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
@AllArgsConstructor
public class FlightGenerationService {

    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final SeatRepository seatRepository;

    private final Random random = new Random();

    public void generateRandomFlights(int count) {
        String[] destinations = {"Paris", "London", "New York", "Tokyo", "Berlin"};
        String[] departures = {"Tallinn", "Venice", "Dallas", "Bejing"};

        for (int i = 0; i < count; i++) {
            AirplaneEntity airplane = new AirplaneEntity();
            int rows = random.nextInt(10) + 5;
            int columns = random.nextInt(4) + 2;
            airplane.setRows(rows);
            airplane.setColumns(columns);
            airplane.setCapacity(rows * columns);
            airplaneRepository.save(airplane);

            for (int row = 1; row <= rows; row++) {
                for (int col = 1; col <= columns; col++) {
                    SeatEntity seat = new SeatEntity();
                    seat.setRow(row);
                    seat.setColumn(col);
                    seat.setPrice(100 + row * 10);
                    seat.setExtraLegroom(random.nextDouble() < 0.3);
                    seat.setIsBooked(random.nextDouble() < 0.2);
                    seat.setAirplane(airplane);
                    seatRepository.save(seat);
                }
            }

            System.out.println("Generated airplane with " + rows + " rows and " + columns + " columns");


            FlightEntity flight = new FlightEntity();
            flight.setDestination(destinations[random.nextInt(destinations.length)]);
            flight.setDeparture(departures[random.nextInt(departures.length)]);
            flight.setDepartureTime(Instant.now().plusSeconds(random.nextInt(7 * 24 * 3600)));
            flight.setPrice(200 + random.nextInt(300));
            flight.setAirplane(airplane);
            flightRepository.save(flight);
        }
    }
}
