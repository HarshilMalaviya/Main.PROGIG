package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Portfolio")
@AllArgsConstructor
@NoArgsConstructor
public class Portfolio {
    private int portfolioId;
    private String portfolioName;
//    Skills that used to build the project
    private String Skills;
}
