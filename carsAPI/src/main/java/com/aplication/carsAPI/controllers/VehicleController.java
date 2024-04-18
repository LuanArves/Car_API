package com.aplication.carsAPI.controllers;

import com.aplication.carsAPI.dtos.CreateVehicleData;
import com.aplication.carsAPI.dtos.UpdateVehicleData;
import com.aplication.carsAPI.dtos.VehicleDetails;
import com.aplication.carsAPI.models.Manufacturer;
import com.aplication.carsAPI.models.Model;
import com.aplication.carsAPI.models.Vehicle;
import com.aplication.carsAPI.pagination.Page;
import com.aplication.carsAPI.repositories.VehicleRepository;
import com.aplication.carsAPI.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    private VehicleService vehicleService;


    @GetMapping
    public Page<VehicleDetails> index(Pageable pageable){
        var page = vehicleService.find(pageable);
        return page.map(VehicleDetails::new);
    }

    @GetMapping("/{id}")
    public VehicleDetails show(@PathVariable("id") Long id){
        var vehicle = vehicleService.getVehicleById(id);
        return new VehicleDetails(vehicle);
    }

    @PostMapping
    public VehicleDetails create(@RequestBody @Validated CreateVehicleData data){
        var vehicle = vehicleService.createVehicle(data);
        return new VehicleDetails(vehicle);
    }

    @PutMapping("/{id}")
    public VehicleDetails update(@PathVariable("id") Long id, @RequestBody @Validated UpdateVehicleData data){
        var vehicle = vehicleService.updateVehicle(data, id);
        return new VehicleDetails(vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id, Vehicle vehicle){
        vehicleService.deleteVehicleById(id);
    }

}
