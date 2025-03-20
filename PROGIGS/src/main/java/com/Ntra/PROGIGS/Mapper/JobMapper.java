package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ClientDetailsForJobDTO;
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
    public JobDto MapToDto1(Jobs jobs) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        // Map Jobs to JobDto
        JobDto jobDto = new ModelMapper().map(jobs, JobDto.class);

        // Extract user details if the user is a client

        if (jobs.getUser() != null) {
            System.out.println("✅ User Found: " + jobs.getUser().getId() + ", Role: " + jobs.getUser().getRole());

            // Only map if the user is a client
            if ("CLIENT".equalsIgnoreCase(String.valueOf(jobs.getUser().getRole()))) {
                ClientDetailsForJobDTO clientDetails = new ClientDetailsForJobDTO();
                clientDetails.setId(jobs.getUser().getId());
                clientDetails.setLocation(jobs.getUser().getProfile().getLocation());
                clientDetails.setJoiningDate(jobs.getUser().getJoiningDate());

                jobDto.setClient(clientDetails);
                System.out.println("✅ Client Mapped for Job ID: " + jobs.getId());
            } else {
                System.out.println("⚠️ User is not a CLIENT for Job ID: " + jobs.getId());
            }
        } else {
            System.out.println("⚠️ No User Associated with Job ID: " + jobs.getId());
        }

        return jobDto;
    }


    public Jobs MapToJob(JobDto jobDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Jobs jobs = new Jobs();
        jobs = new ModelMapper().map(jobDto,Jobs.class);
        return jobs;
    }


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
