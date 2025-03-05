package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Profile")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String description;
    private String status;
    private String whyRejected;
    private String imageUrl;
    @ElementCollection
    private List<String> skills;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    private BankDetails bank;
    @OneToOne(mappedBy = "profile")
    private User user;

////    Aditional Section
//    @OneToMany()
//    private Review review;
    // portfolio mapping is not done
//    @OneToMany
//    private Portfolio portfolio;

}
