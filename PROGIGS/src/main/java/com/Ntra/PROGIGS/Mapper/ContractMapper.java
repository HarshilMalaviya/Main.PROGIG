package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.CertificateDto;
import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.Entity.Certificates;
import com.Ntra.PROGIGS.Entity.Contract;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ContractDto MapToDto(Contract contract){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ContractDto contractDto = new ContractDto();
        contractDto = new ModelMapper().map(contract,ContractDto.class);
        return contractDto;
    }

    public Contract MapToContract(ContractDto contractDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Contract contract = new Contract();
        contract = new ModelMapper().map(contractDto,Contract.class);
        return contract;
    }
}
