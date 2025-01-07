package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "Portfolio")

public class Portfolio implements Serializable {
    @Id
    private int portfolioId;
    private String portfolioName;
//    Skills that used to build the project
    private String Skills;
}
