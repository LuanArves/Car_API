package com.aplication.carsAPI.services;

import com.aplication.carsAPI.dtos.CreateVehicleData;
import com.aplication.carsAPI.dtos.UpdateVehicleData;
import com.aplication.carsAPI.models.Vehicle;
import com.aplication.carsAPI.pagination.Page;
import com.aplication.carsAPI.repositories.ModelRepository;
import com.aplication.carsAPI.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;

    public Page<Vehicle> find(Pageable pageable) {
        var page = vehicleRepository.findAll(pageable);
        return new Page<>(page);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Vehicle not found"));
    }

    public Vehicle createVehicle(CreateVehicleData data) {
        var model = modelRepository.findById(data.getModelId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Model not found"));

        var vehicle = new Vehicle(
                data.getKilometers(),
                data.getColor(),
                data.getDescription(),
                data.getYear(),
                model
        );

        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle updateVehicle(UpdateVehicleData data, Long id) {
        var vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Vehicle not found"));

        var model = modelRepository.findById(data.getModelId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Model not found"));

        vehicle.setKilometers(data.getKilometers());
        vehicle.setColor(data.getColor());
        vehicle.setDescription(data.getDescription());
        vehicle.setYear(data.getYear());
        vehicle.setModel(model);

        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public void deleteVehicleById(Long id) {
        if(!vehicleRepository.existsById(id)){
            throw new ResponseStatusException(NOT_FOUND, "Vehicle not found");
        }
        vehicleRepository.deleteById(id);

    }
}
