package com.Ntra.PROGIGS.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore  // Prevents circular reference
    private List<Proposals> proposals;

    @OneToOne(mappedBy = "jobs")
    @JsonIgnore
    private Review reviews;

    @OneToOne(mappedBy = "jobs")
    @JsonIgnore
    private Contract contracts;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id",referencedColumnName = "Job_ID")
    @JsonIgnore
    private List<Milestone> milestones;
}
