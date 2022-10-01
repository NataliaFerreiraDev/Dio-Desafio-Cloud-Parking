package br.com.dio.parking.controller;

import br.com.dio.parking.model.Parking;
import br.com.dio.parking.model.dto.ParkingCreateDTO;
import br.com.dio.parking.model.dto.ParkingDTO;
import br.com.dio.parking.model.mapper.ParkingMapper;
import br.com.dio.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Find all parking")
    public ResponseEntity<List<ParkingDTO>>findAll(){

        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find by id")
    public ResponseEntity<ParkingDTO>findById(@PathVariable String id){

        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Create parking")
    public ResponseEntity<ParkingDTO>create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking = parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
