package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.ProposalsDto;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.Proposals;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProposalMapper {

    @Autowired
    public ModelMapper modelMapper;

    public Proposals MapToDto(Proposals proposals){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProposalsDto proposalDto = new ProposalsDto();
        proposals = new ModelMapper().map(proposals, Proposals.class);
        return proposals;
    }

    public Proposals MapToProposal(ProposalsDto proposalsDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Proposals proposals = new Proposals();
        proposals = new ModelMapper().map(proposalsDto,Proposals.class);
        return proposals;
    }
}
