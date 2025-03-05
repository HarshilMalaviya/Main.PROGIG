package com.Ntra.PROGIGS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Entity
@Data
@Table(name = "Profile")

public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String Education;
    private String Articles;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Certificates> Certification;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id")
    @JsonIgnore
    private BankDetails bank;
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Portfolio> portfolio;


    //    this come from admin side Don't show in user site
    private String status;
    private String whyRejected;




    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;
}