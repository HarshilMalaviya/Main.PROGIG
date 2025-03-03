package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clientName;
    private String freelancerName;
    private String amount;
    private LocalDate transactionDate;
    private String jobTitle;
    private String jobDescription;
    @OneToOne(mappedBy = "transaction")
    private Invoice invoice;
}
