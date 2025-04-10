package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Status;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class JobDtoForCard {

    private int id;
    private String clientName;
    private String title;
    private String description;
    private String Location;
    private int amount;
    private Status status;
    @ElementCollection
    private List<String> skillsRequired;
    private int proposalsCount;

}
