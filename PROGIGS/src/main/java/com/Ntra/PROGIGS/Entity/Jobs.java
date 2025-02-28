package com.Ntra.PROGIGS.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "job")
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Job_ID")
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "Job_ID")
    private List<Proposals> proposals;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "Job_ID")
    private List<Review> reviews ;

}