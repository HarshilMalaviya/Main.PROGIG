package com.Ntra.PROGIGS.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "Proposals")
public class Proposals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    private String clientName;
    private String jobTitle;
    private String freelancerName;
    private String freelancerEmail;
    private String description;
    private Long bid;
    private Date finishingTime;
    @Enumerated(value=EnumType.STRING)
    private PropsalStatus status;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnore // Prevents nested Jobs inside Proposals
    private Jobs jobs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "freelancer_id", nullable = false)
    @JsonIgnore // Prevents nested Freelancer inside Proposals
    private User user;
}

