package com.Ntra.PROGIGS.DTOs;

import lombok.Builder;
import lombok.Data;

@Data

public class BankDetailsDTO {


    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String branch;
}
