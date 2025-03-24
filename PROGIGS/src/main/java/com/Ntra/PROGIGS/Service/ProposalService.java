package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ProposalsDto;
import com.Ntra.PROGIGS.Entity.Proposals;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProposalService {
    public Proposals saveProposal(ProposalsDto proposals,int jobid);
    public List<Proposals> getProposalFromJob(int jobid);



}
