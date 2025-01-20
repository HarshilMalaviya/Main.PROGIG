package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Entity.Jobs;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BankDetailsMapper {
    @Autowired
    public ModelMapper modelMapper;

    public BankDetailsDTO bankDetailsToDTO(BankDetails bankDetails){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO();
        bankDetailsDTO = new ModelMapper().map(bankDetails,BankDetailsDTO.class);
        return bankDetailsDTO;
    }

    public BankDetails DTOToBankDetails(BankDetailsDTO bankDetailsDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        BankDetails bankDetails = new BankDetails();
        bankDetails = new ModelMapper().map(bankDetailsDTO,BankDetails.class);
        return bankDetails;
    }
}
