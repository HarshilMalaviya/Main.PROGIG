package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Profile")
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
//  freelancer + client basic profile
    private String Address;
    private String zipCode;
    private String city;
    private String State;
    private String Country;
//  Client detail
    private String CompanyName;
    private String Location;
//    Add ON Info
    private String Experience;
    private String Education;
    private String Articles;
    private String Certification;
//    Aditional Section
    private Review review;
    private Portfolio portfolio;

}
