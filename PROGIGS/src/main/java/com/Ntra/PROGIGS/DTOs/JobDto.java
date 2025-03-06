package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.*;
import com.Ntra.PROGIGS.Entity.Module;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class JobDto {

    private int id;
    private String title;
    private String description;
    @ElementCollection
    private List<String> skillsRequired;
    private String duration;
    private int amount;
    private Pay0ut_Methods payout_methods;
    private Status status;
    private List<Module> modules;
}
