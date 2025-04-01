package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;

import java.util.List;

public interface BankDetailService {

    BankDetailsDTO saveBankDetail(BankDetailsDTO bankDetailsDTO);

    BankDetailsDTO editBankDetail(BankDetailsDTO bankDetails, int id);

    void deleteBankDetail(int id);
}
