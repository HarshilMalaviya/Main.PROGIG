package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
import com.Ntra.PROGIGS.Entity.Contract;

import java.util.List;

public interface ContractService {
    public ContractDto saveContract(ContractDto contract,int jobid);
    public ContractDto getContractById(int contractid);

    void editeContractStatus(int contractid);

    public List<JobDtoForCard> activeContract();


    List<ContractDto> getContract();

    ContractDto closeContract(int contractId);
}
