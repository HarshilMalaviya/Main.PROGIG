package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.JobDto;

import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    @Autowired
    public UserRepo userRepo;

    @Autowired
    public ModelMapper modelMapper;

    public JobDto MapToDto(Jobs jobs){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        JobDto jobDto = new JobDto();
        jobDto = new ModelMapper().map(jobs,JobDto.class);
        return jobDto;
    }

    public Jobs MapToJob(JobDto jobDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Jobs jobs = new Jobs();
        jobs = new ModelMapper().map(jobDto,Jobs.class);
        return jobs;
    }
//    public JobDtoForCard MapToJobDtoforCard(Jobs jobs){
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//        JobDtoForCard jobDtoForCard = new JobDtoForCard();
//        User user = jobs.getUser();
//        jobDtoForCard.setLocation(user.getProfile().getLocation());
//        jobDtoForCard = new ModelMapper().map(jobs,JobDtoForCard.class);
//        return jobDtoForCard;
//    }

    public JobDtoForCard MapToJobDtoforCard(Jobs jobs) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.typeMap(Jobs.class, JobDtoForCard.class).addMappings(mapper -> {
            mapper.skip(JobDtoForCard::setProposalsCount);
        });


        // Map Jobs to JobDtoForCard using the configured ModelMapper
        JobDtoForCard jobDtoForCard = modelMapper.map(jobs, JobDtoForCard.class);

        // Set the location manually after mapping
        User user = jobs.getUser();
        if (user != null && user.getProfile() != null) {
            jobDtoForCard.setLocation(user.getProfile().getLocation());
        }

        // Explicitly set the proposal count to avoid mapping issues
        int proposalCount = (jobs.getProposals() != null) ? jobs.getProposals().size() : 0;
        jobDtoForCard.setProposalsCount(proposalCount);

        return jobDtoForCard;
    }




    public Jobs MapToJobs(JobDtoForCard jobDtoForCard){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Jobs jobs = new Jobs();
        jobs = new ModelMapper().map(jobDtoForCard,Jobs.class);
        return jobs;
    }


}
