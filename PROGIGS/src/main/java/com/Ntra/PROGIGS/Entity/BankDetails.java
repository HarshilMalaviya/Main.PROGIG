package com.Ntra.PROGIGS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bank_details")

public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String branch;
    @OneToOne(mappedBy = "bank")
    @JsonIgnore
    private Profile profile;

    public void setAccountNumber(String accountNumber) {
        if (accountNumber != null && accountNumber.matches("\\d{12}")) { // Ensures exactly 12 digits
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException("Account number must be exactly 12 digits.");
        }
    }

    // Getter to return the masked account number in API responses
    public String getAccountNumber() {
        if (this.accountNumber != null && this.accountNumber.length() == 12) {
            return "********" + this.accountNumber.substring(8);
        }
        return null;
    }
}
