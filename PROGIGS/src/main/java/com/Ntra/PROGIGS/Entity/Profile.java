package com.Ntra.PROGIGS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "Profile")
@JsonIgnoreProperties(ignoreUnknown = true)  // ✅ Ignores unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private String fieldOfWork;
    @Column(nullable = true)
    private String phone ;
    private String imageUrl;
    @ElementCollection
    private List<String> skills;
    //  Client detail
    private String CompanyName;
    private String Location;
    @Enumerated(value = EnumType.STRING)
    private Profile_Status status;
    private boolean isVerified = false;
    //    Add ON Info
    private String hourlyRate;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Education> education;

    //    private String Articles;
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
    private String whyRejected;




    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;
}