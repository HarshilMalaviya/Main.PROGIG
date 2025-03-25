package com.Ntra.PROGIGS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Pattern(regexp = "\\d{12}", message = "Account number must be exactly 12 digits.")
    @Size(min = 12, max = 12, message = "Account number must be exactly 12 digits.")
    private String accountNumber;
    private String ifscCode;
    private String branch;
    @OneToOne(mappedBy = "bank")
    @JsonIgnore
    private Profile profile;


    // Getter to return the masked account number in API responses
    public String getAccountNumber() {
        if (this.accountNumber != null && this.accountNumber.length() == 12) {
            return "********" + this.accountNumber.substring(8);
        }
        return null;
    }
}
