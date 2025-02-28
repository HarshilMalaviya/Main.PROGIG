package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Entity.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private Integer id;
    //  freelancer + client basic profile
//    private String Address;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id")
    private BankDetails bank;
    //    Aditional Section
//    private List<Review> review;
//    private Portfolio portfolio;


}
