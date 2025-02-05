package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bank_details")

public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String branch;

}
