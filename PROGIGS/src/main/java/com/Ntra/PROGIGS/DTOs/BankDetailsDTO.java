package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data

public class BankDetailsDTO {

    private String bankName;
    private String accountHolderName;
    @Pattern(regexp = "\\d{12}", message = "Account number must be exactly 12 digits.")
    @Size(min = 12, max = 12, message = "Account number must be exactly 12 digits.")
    private String accountNumber;
    private String ifscCode;
    private String branch;
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
