package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "Portfolio")
public class Portfolio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int portfolioId;
    @Column(name = "PortfolioTitle")
    private String portfolioTitle;
   @Column(name = "Skills")
   private List<String> skills;
   @Column(name = "Description")
   private String description;
   @Column(name = "PortfolioImage")
    private String portfolioImage;
}
