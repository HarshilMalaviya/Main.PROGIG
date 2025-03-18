package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.*;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDtoForViewCard {
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
    private String fieldOfWork;
    private String Location;
    @Enumerated(value = EnumType.STRING)
    private Profile_Status status;
    //    Add ON Info
    private String hourlyRate;
    private List<com.Ntra.PROGIGS.Entity.Education> Education;
    //    private String Articles;
    private List<Certificates> Certification;
    private List<Portfolio> portfolio;
    private User user;
}
