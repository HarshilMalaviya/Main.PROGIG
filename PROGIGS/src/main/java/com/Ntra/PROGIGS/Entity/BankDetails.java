package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Data

public class BankDetails {
    private int id;
    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String branch;
    @OneToOne
    @MapKeyColumn(name = "id")
    private User user;

}
