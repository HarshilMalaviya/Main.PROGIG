package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.DTOs.ProposalsDto;
import com.Ntra.PROGIGS.DTOs.ProposalsDtoForGet;
import com.Ntra.PROGIGS.Entity.*;
import com.Ntra.PROGIGS.Repository.ReviewRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProposalMapper {

    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    private ReviewRepo reviewRepo;

    public ProposalsDto MapToDto(Proposals proposals){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProposalsDto proposalDto = new ProposalsDto();
        proposalDto = new ModelMapper().map(proposals, ProposalsDto.class);
        return proposalDto;
    }

    public ProposalsDtoForGet MapptoProposalDto(Proposals proposals) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProposalsDtoForGet profileDto = new ProposalsDtoForGet();
        User user = proposals.getUser();
        int totalJobs = user.getJobs().size();
        long completedJobs = user.getJobs().stream()
                .filter(job -> "COMPLETED".equalsIgnoreCase(job.getStatus().name())) // Use .name() for enums
                .count();

        List<Review> reviews = reviewRepo.findByUser(user);
        double totalRating = reviews.stream().mapToDouble(Review::getReview).sum();
        int totalReviews = reviews.size();

        if (reviews.isEmpty()) {
            totalRating=0.0;
            totalReviews= 0;
        }

        profileDto.setName(user.getProfile().getFirstName() + " " + user.getProfile().getLastName());
        profileDto.setUsername(user.getUsername());
        profileDto.setRating((totalRating / totalReviews));
        profileDto.setReviews(totalReviews);
        profileDto.setStatus(proposals.getStatus());
        profileDto.setSuccessRate((totalJobs == 0) ? 0 : ((double) completedJobs / totalJobs) * 100);
        profileDto.setExpertise(user.getProfile().getSkills());
        profileDto.setDescription(proposals.getDescription());
        profileDto.setCountry(user.getProfile().getLocation());
        profileDto.setBid(proposals.getBid());
        profileDto.setFinishingTime(proposals.getFinishingTime());
        profileDto.setImage(user.getProfile().getImageUrl());
        return profileDto;
    }

    public Proposals MapToProposal(ProposalsDto proposalsDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Proposals proposals = new Proposals();
        proposals = new ModelMapper().map(proposalsDto,Proposals.class);
        return proposals;
    }
}
