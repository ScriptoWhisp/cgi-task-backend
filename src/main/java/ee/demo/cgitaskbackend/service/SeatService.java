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

import java.util.ArrayList;
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
        // Fetch the airplane and seat schema
        AirplaneEntity airplane = airplaneRepository.findById(airplaneId).orElseThrow();
        SeatEntity[][] seatSchema = getSeatSchema(airplane.getRows(), airplane.getColumns(), airplaneId);

        // We will store the recommended seats in the same matrix structure as a boolean flag
        boolean[][] recommendedFlags = new boolean[airplane.getRows()][airplane.getColumns()];

        // If seatsCount == 1, we simply mark all valid seats as recommended
        if (criteria.seatsCount() == 1) {
            for (int i = 0; i < airplane.getRows(); i++) {
                for (int j = 0; j < airplane.getColumns(); j++) {
                    SeatEntity seat = seatSchema[i][j];
                    if (seat != null && meetsCriteria(seat, criteria, airplane.getRows(), airplane.getColumns())) {
                        recommendedFlags[i][j] = true;
                    }
                }
            }
        } else {
            // If seatsCount > 1, we look for consecutive seats in each row
            for (int i = 0; i < airplane.getRows(); i++) {
                int consecutiveCount = 0; // how many consecutive suitable seats found
                List<Integer> candidatePositions = new ArrayList<>(); // store column indices of potential seats

                for (int j = 0; j < airplane.getColumns(); j++) {
                    SeatEntity seat = seatSchema[i][j];
                    if (seat != null && meetsCriteria(seat, criteria, airplane.getRows(), airplane.getColumns())) {
                        consecutiveCount++;
                        candidatePositions.add(j);
                    } else {
                        // reset if we hit a seat that doesn't meet criteria
                        consecutiveCount = 0;
                        candidatePositions.clear();
                    }

                    // if we have enough seats in a row, mark them recommended
                    if (consecutiveCount == criteria.seatsCount()) {
                        // mark recommended
                        for (int c : candidatePositions) {
                            recommendedFlags[i][c] = true;
                        }
                        consecutiveCount = 0;
                        candidatePositions.clear();
                    }
                }
            }
        }

        // Convert seatSchema to SeatDto[][] and set recommended field
        SeatDto[][] seatDtoSchema = new SeatDto[airplane.getRows()][airplane.getColumns()];
        for (int i = 0; i < airplane.getRows(); i++) {
            for (int j = 0; j < airplane.getColumns(); j++) {
                SeatEntity seat = seatSchema[i][j];
                if (seat != null) {
                    SeatDto dto = seatMapper.toDto(seat);
                    dto.setIsRecommended(recommendedFlags[i][j]);
                    seatDtoSchema[i][j] = dto;
                } else {
                    seatDtoSchema[i][j] = null; // no seat at this position
                }
            }
        }

        return seatDtoSchema;
    }

    private boolean meetsCriteria(SeatEntity seat, PreferredSeatCriteria criteria, int totalRows, int totalColumns) {
        // Check if the seat is free
        if (Boolean.TRUE.equals(seat.getIsBooked())) {
            return false;
        }

        // Check price limit
        if (criteria.price() != null && seat.getPrice() != null && seat.getPrice() > criteria.price()) {
                return false;
            }


        // Check extra legroom
        if (Boolean.TRUE.equals(criteria.extraLegroom()) && !Boolean.TRUE.equals(seat.getExtraLegroom())) {
            return false;
        }

        // Check near window (column == 1 or column == totalColumns)
        if (Boolean.TRUE.equals(criteria.nearWindow()) && !seatIsAtWindow(seat, totalColumns)) {
                return false;
        }


        // Check near exit (row == 1 or row == totalRows)
        if (Boolean.TRUE.equals(criteria.nearExit()) && !seatIsNearExit(seat, totalRows)) {
                return false;
        }


        // If all conditions are satisfied
        return true;
    }

    private boolean seatIsAtWindow(SeatEntity seat, int totalColumns) {
        if (seat.getColumn() != null) {
            return seat.getColumn() == 1 || seat.getColumn().equals(totalColumns);
        }
        return false;
    }


    private boolean seatIsNearExit(SeatEntity seat, int totalRows) {
        if (seat.getRow() != null) {
            return seat.getRow() == 1 || seat.getRow().equals(totalRows);
        }
        return false;
    }



}
