package com.aplication.carsAPI.repositories;

import com.aplication.carsAPI.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
