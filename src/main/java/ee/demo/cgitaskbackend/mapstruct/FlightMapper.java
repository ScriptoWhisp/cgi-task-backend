package ee.demo.cgitaskbackend.mapstruct;

import ee.demo.cgitaskbackend.domain.FlightEntity;
import ee.demo.cgitaskbackend.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "destination", target = "destination")
    @Mapping(source = "departure", target = "departure")
    @Mapping(source = "departureTime", target = "departureTime")
    @Mapping(source = "price", target = "price")
    @Mapping(target = "airplane", ignore = true)
    FlightEntity toEntity(FlightDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "destination", target = "destination")
    @Mapping(source = "departure", target = "departure")
    @Mapping(source = "departureTime", target = "departureTime")
    @Mapping(source = "price", target = "price")
    FlightDto toDto(FlightEntity entity);


}
