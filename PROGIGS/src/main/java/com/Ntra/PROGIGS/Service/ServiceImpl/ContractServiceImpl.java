package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.Proposals;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.ContractMapper;
import com.Ntra.PROGIGS.Repository.ContractRepo;
import com.Ntra.PROGIGS.Repository.JobRepo;
import com.Ntra.PROGIGS.Repository.ProposalsRepo;
import com.Ntra.PROGIGS.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private ProposalsRepo proposalsRepo;
    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;

    @Override
    public Contract saveContract(ContractDto contract,int jobid) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        contract.setClient(user);
        Jobs jobs=jobRepo.findById(jobid);
        contract.setJobs(jobs);
        Optional<Proposals> proposals=proposalsRepo.findHiredProposalByJobId(jobid);
        contract.setFreelancer(proposals.get().getUser());
        return contractRepo.save(contractMapper.MapToContract(contract));
    }

    @Override
    public Contract getContractById(int contractid) {
        return contractRepo.findById(contractid).orElseThrow(()->new RuntimeException("Contract not found"));
    }

    @Override
    public Contract editeContractStatus(ContractDto contract, int contractid) {
        Contract contract1 = contractRepo.findById(contractid).orElseThrow(()->new RuntimeException("Contract not found"));
        contract1.setStatus(contract.getStatus());
        return contractRepo.save(contract1);
    }
}
