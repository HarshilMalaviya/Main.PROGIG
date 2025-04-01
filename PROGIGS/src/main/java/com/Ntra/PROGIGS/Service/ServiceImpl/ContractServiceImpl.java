package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.*;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.ContractMapper;
import com.Ntra.PROGIGS.Mapper.JobMapper;
import com.Ntra.PROGIGS.Repository.ContractRepo;
import com.Ntra.PROGIGS.Repository.JobRepo;
import com.Ntra.PROGIGS.Repository.ProposalsRepo;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private UserRepo userRepo;
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

    @Override
    public List<JobDto> activeContract() {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        List<Contract> activeContract = new ArrayList<>();
        if ("CLIENT".equals(user.getRole().toString())) {
           activeContract  = contractRepo.findAllByClientAndStatus(user, ContractStatus.ACTIVE);
        } else if ("FREELANCER".equals(user.getRole().toString())) {
            activeContract  = contractRepo.findAllByFreelancerAndStatus(user, ContractStatus.ACTIVE);
        }else {
            throw new RuntimeException("You are not a client or freelancer");
        }

        List<JobDto> activeJobs = new java.util.ArrayList<>();
        for (Contract contract : activeContract) {
            Jobs job = contract.getJobs();
            activeJobs.add(jobMapper.MapToDto(job));
        }
        return activeJobs;
    }

    @Override
    public List<ContractDto> getContract() {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        List<Contract> contracts = new ArrayList<>();
        if ("CLIENT".equals(user.getRole().toString())) {
            contracts = contractRepo.findAllByClient(user);
        } else if ("FREELANCER".equals(user.getRole().toString())) {
            contracts = contractRepo.findAllByFreelancer(user);
        } else {
            throw new RuntimeException("You are not a client or freelancer");
        }
        List<ContractDto> contractDtos = new ArrayList<>();
        for (Contract contract : contracts) {
            contractDtos.add(contractMapper.MapToDto(contract));
        }
        return contractDtos;
    }




}
