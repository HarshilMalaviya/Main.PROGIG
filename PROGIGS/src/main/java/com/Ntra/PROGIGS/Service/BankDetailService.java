package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;

import java.util.List;

public interface BankDetailService {

    public BankDetails saveBankDetail(BankDetailsDTO bankDetailsDTO);
//    public BankDetails getBankDetail(int id);

    public List<BankDetailsDTO> getBankDetail();
    public BankDetails editBankDetail(BankDetails bankDetails);
    public void deleteBankDetail(int id);

}
