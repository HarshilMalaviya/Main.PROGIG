package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Entity.Education;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Entity.Review;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
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
    //    Add ON Info
    private String hourlyRate;
//    private String Articles;
}
