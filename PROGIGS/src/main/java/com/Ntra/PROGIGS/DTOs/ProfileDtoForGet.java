package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Entity.Certificates;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDtoForGet {
    private int id;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    @Column(nullable = true)
    private String phone ;
    private String imageUrl;
    @ElementCollection
    private List<String> skills;
    //  Client detail
    private String CompanyName;
    private String Location;
    //    Add ON Info
    private String hourlyRate;
    private List<String> Education;
//    private String Articles;
    private List<Certificates> Certification;
    private BankDetails bank;
    private List<Portfolio> portfolio;

}
