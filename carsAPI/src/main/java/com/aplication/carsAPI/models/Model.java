package com.aplication.carsAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor //Construtor escondido
@NoArgsConstructor
@Entity(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "manufacturer_id")
    @ManyToOne
    private Manufacturer manufacturer;

}
