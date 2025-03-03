package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.*;
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
    private String providers_name;
    private String providers_email;

    private Review reviews ;
    private List<Proposals> proposals;


}
