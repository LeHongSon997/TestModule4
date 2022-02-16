package com.sonle.testmodule4.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Ten thanh pho khong duoc de trong")
    private String cityName;
    @NotEmpty(message = "Dien tich khong duoc de trong")
    private Long area;
    @NotEmpty(message = "Dan so khong duoc de trong")
    private Long population;
    @NotEmpty(message = "GDP khong duoc de trong")
    private double GDP;
    private String description;

    @ManyToOne
    Country country;
}
