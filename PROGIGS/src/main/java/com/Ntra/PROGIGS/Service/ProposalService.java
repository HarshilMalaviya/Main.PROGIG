package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ProposalsDto;
import com.Ntra.PROGIGS.DTOs.ProposalsDtoForGet;
import com.Ntra.PROGIGS.Entity.Proposals;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProposalService {
    public Proposals saveProposal(ProposalsDto proposals,int jobid);
    public List<ProposalsDtoForGet> getProposalFromJob(int jobid);
    public void changeStatus(int proposalid, ProposalsDto proposals);



}
