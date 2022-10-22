package br.com.dio.parking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import br.com.dio.parking.controller.dto.ParkingCreateDTO;
import br.com.dio.parking.controller.dto.ParkingDTO;
import br.com.dio.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }

    public Parking toParking(ParkingDTO dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }

    public Parking toParkingCreate(ParkingCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }

}