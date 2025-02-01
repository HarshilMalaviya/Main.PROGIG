package com.Ntra.PROGIGS.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Profile")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
//  freelancer + client basic profile

//    private String Address;

    private Long zipCode;

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
    @OneToOne
    @JoinColumn(name = "bank_id")
    private BankDetails bank;

    private BankDetails bankDetails;
//    Aditional Section
//    @Nullable
//    private Review review;
//    @Nullable
//    private Portfolio portfolio;

}
