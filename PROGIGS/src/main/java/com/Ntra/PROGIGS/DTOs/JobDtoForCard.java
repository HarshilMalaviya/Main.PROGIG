package com.Ntra.PROGIGS.DTOs;

import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class JobDtoForCard {

    private int id;
    private String title;
    private String description;
    private String Location;
    private int amount;
    @ElementCollection
    private List<String> skillsRequired;
    private int proposalsCount;

}
