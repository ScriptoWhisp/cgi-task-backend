package ee.demo.cgitaskbackend.service;

import ee.demo.cgitaskbackend.criteria.PreferredSeatCriteria;
import ee.demo.cgitaskbackend.domain.AirplaneEntity;
import ee.demo.cgitaskbackend.domain.SeatEntity;
import ee.demo.cgitaskbackend.dto.SeatDto;
import ee.demo.cgitaskbackend.mapstruct.SeatMapper;
import ee.demo.cgitaskbackend.repository.AirplaneRepository;
import ee.demo.cgitaskbackend.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final AirplaneRepository airplaneRepository;
    private final SeatMapper seatMapper;

    private SeatEntity[][] getSeatSchema(int rows, int columns, int airplaneId) {
        SeatEntity[][] seatSchema = new SeatEntity[rows][columns];
        List<SeatEntity> seatEntities = seatRepository.findAllByAirplane_Id(airplaneId);
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                int finalI = i;
                int finalJ = j;
                seatSchema[i - 1][j - 1] = seatEntities.stream()
                        .filter(seatEntity -> seatEntity.getRow() == finalI && seatEntity.getColumn() == finalJ)
                        .findFirst()
                        .orElse(null);
            }
        }
        return seatSchema;
    }

    public SeatDto[][] getSeatSchemaWithRecommendations(int airplaneId, PreferredSeatCriteria criteria) {
        // Some algorithm to recommend seats
        AirplaneEntity airplane = airplaneRepository.findById(airplaneId).orElseThrow();
        return null;

    }
}
