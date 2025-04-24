package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
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
    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;
    @Override
    public BankDetailsDTO saveBankDetail(BankDetailsDTO bankDetailsDTO) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        Profile profile = user.getProfile();

        BankDetails bankDetails = bankDetailsMapper.DTOToBankDetails(bankDetailsDTO);
        bankDetails.setProfile(profile); // optional if needed
        profile.setBank(bankDetails);    // set in both directions if bidirectional

//        bankDetailsRepo.save(bankDetails); // only one instance is used

        return bankDetailsDTO;
    }


    @Override
    public BankDetailsDTO editBankDetail(BankDetailsDTO bankDetails,int id) {
        Profile profile = getAuthenticatedUser.getAuthenticatedUser().getProfile();
        BankDetails bankDetails1= bankDetailsRepo.findById(id).get();
        BankDetailsDTO bankDetailsDTO=bankDetailsMapper.bankDetailsToDTO(bankDetails1);
        bankDetailsDTO.setBankName(bankDetails.getBankName());
        bankDetailsDTO.setAccountHolderName(bankDetails.getAccountHolderName());
        bankDetailsDTO.setAccountNumber(bankDetails.getAccountNumber());
        bankDetailsDTO.setIfscCode(bankDetails.getIfscCode());
        bankDetailsDTO.setBranch(bankDetails.getBranch());
        bankDetailsDTO.setProfile(profile);
        bankDetailsRepo.save(bankDetailsMapper.DTOToBankDetails(bankDetailsDTO));
        return bankDetailsDTO;

    }

//    @Override
//    public BankDetailsDTO editBankDetail(BankDetailsDTO bankDetails,int id) {;
//        BankDetails bankDetails1= bankDetailsRepo.findById(id).get();
//        BankDetailsDTO bankDetailsDTO=bankDetailsMapper.bankDetailsToDTO(bankDetails1);
//        bankDetailsDTO.setBankName(bankDetails.getBankName());
//        bankDetailsDTO.setAccountHolderName(bankDetails.getAccountHolderName());
//        bankDetailsDTO.setAccountNumber(bankDetails.getAccountNumber());
//        bankDetailsDTO.setIfscCode(bankDetails.getIfscCode());
//        bankDetailsDTO.setBranch(bankDetails.getBranch());
//        bankDetailsRepo.save(bankDetailsMapper.DTOToBankDetails(bankDetailsDTO));
//        return bankDetailsDTO;
//
//    }
    @Override
    public void deleteBankDetail(int id) {
        bankDetailsRepo.deleteById(id);
    }
}
