package com.aplication.carsAPI.repositories;

import com.aplication.carsAPI.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
