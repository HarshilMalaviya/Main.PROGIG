package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "Portfolio")
public class Portfolio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int portfolioId;
    @Column(name = "PortfolioName")
    private String portfolioName;
//    Skills that used to build the project
   @Column(name = "Skills")

    private String Skills;
}
