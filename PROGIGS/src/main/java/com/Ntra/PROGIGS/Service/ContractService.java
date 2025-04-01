package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.Contract;

import java.util.List;

public interface ContractService {
    public Contract saveContract(ContractDto contract,int jobid);
    public Contract getContractById(int contractid);
    public Contract editeContractStatus(ContractDto contract,int contractid);

    public List<JobDto> activeContract();


    List<ContractDto> getContract();
}
