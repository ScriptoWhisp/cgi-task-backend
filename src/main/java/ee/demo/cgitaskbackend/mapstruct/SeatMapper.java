package ee.demo.cgitaskbackend.mapstruct;


import ee.demo.cgitaskbackend.domain.SeatEntity;
import ee.demo.cgitaskbackend.dto.SeatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "row", target = "row")
    @Mapping(source = "column", target = "column")
    @Mapping(source = "isBooked", target = "isBooked")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "extraLegroom", target = "extraLegroom")
    @Mapping(target = "airplane", ignore = true)
    SeatEntity toEntity(SeatDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "row", target = "row")
    @Mapping(source = "column", target = "column")
    @Mapping(source = "isBooked", target = "isBooked")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "extraLegroom", target = "extraLegroom")
    SeatDto toDto(SeatEntity entity);



}
