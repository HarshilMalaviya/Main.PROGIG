package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;

import java.util.List;

public interface BankDetailService {

    BankDetails saveBankDetail(BankDetailsDTO bankDetailsDTO);

    BankDetails editBankDetail(BankDetails bankDetails);

    void deleteBankDetail(int id);
}
