package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Mapper.BankDetailsMapper;
import com.Ntra.PROGIGS.Repository.BankDetailsRepo;
import com.Ntra.PROGIGS.Service.BankDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BankDetailsServiceImpl implements BankDetailService {
    @Autowired
    private BankDetailsRepo bankDetailsRepo;
    @Autowired
    private BankDetailsMapper bankDetailsMapper;
    @Override
    public BankDetails saveBankDetail(BankDetailsDTO bankDetailsDTO) {
        return bankDetailsRepo.save(bankDetailsMapper.DTOToBankDetails(bankDetailsDTO));
    }


    @Override
    public BankDetails editBankDetail(BankDetails bankDetails) {
      BankDetails bankDetails1= bankDetailsRepo.findById(bankDetails.getId()).get();
      BankDetailsDTO bankDetailsDTO=bankDetailsMapper.bankDetailsToDTO(bankDetails1);
      bankDetailsDTO.setBankName(bankDetails.getBankName());
      bankDetailsDTO.setAccountHolderName(bankDetails.getAccountHolderName());
      bankDetailsDTO.setAccountNumber(bankDetails.getAccountNumber());
      bankDetailsDTO.setIfscCode(bankDetails.getIfscCode());
      bankDetailsDTO.setBranch(bankDetails.getBranch());
      return bankDetailsRepo.save(bankDetailsMapper.DTOToBankDetails(bankDetailsDTO));

    }
    @Override
    public void deleteBankDetail(int id) {
        bankDetailsRepo.deleteById(id);
    }
}
