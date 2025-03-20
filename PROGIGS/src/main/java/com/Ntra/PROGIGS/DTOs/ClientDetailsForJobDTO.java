package com.Ntra.PROGIGS.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDetailsForJobDTO {
    private int id;
    private String location;
    private LocalDate joiningDate;

}