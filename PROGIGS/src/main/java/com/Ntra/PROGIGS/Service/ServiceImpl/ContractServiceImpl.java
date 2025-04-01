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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private WebSocketNotificationServiceImpl webSocketNotificationService;
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
    public ContractDto saveContract(ContractDto contract,int jobid) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        contract.setClient(user);
        Jobs jobs=jobRepo.findById(jobid);
        contract.setJobs(jobs);
        contract.setStatus(ContractStatus.ACTIVE);
        Optional<Proposals> proposals=proposalsRepo.findHiredProposalByJobId(jobid);
        contract.setFreelancer(proposals.get().getUser());
        contractRepo.save(contractMapper.MapToContract(contract));
        return contract;
    }

    @Override
    public ContractDto getContractById(int contractid) {
        return contractMapper.MapToDto(contractRepo.findById(contractid).orElseThrow(()->new RuntimeException("Contract not found")));
    }

    @Override
    public void editeContractStatus(int contractid) {
        Contract contract1 = contractRepo.findById(contractid).orElseThrow(()->new RuntimeException("Contract not found"));
        contract1.setStatus(ContractStatus.CLOSED);
        webSocketNotificationService.sendContractClosedNotification(contract1.getFreelancer().getId(), contract1.getJobs().getId());
        contractRepo.save(contract1);

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




}
