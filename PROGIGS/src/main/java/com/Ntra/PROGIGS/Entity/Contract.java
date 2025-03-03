package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Jobs jobs;
    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer; // References User as Freelancer

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client; // References User as Client


}
