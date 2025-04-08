package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ClientDetailsForJobDTO;
import com.Ntra.PROGIGS.DTOs.JobDto;

import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
        if (jobs == null) {
            return null; // Handle null case safely
        }

        JobDtoForCard jobDtoForCard = new JobDtoForCard();

        // Map simple fields directly
        jobDtoForCard.setId(jobs.getId());
        jobDtoForCard.setTitle(jobs.getTitle());
        jobDtoForCard.setDescription(jobs.getDescription());
        jobDtoForCard.setAmount(jobs.getAmount());
        jobDtoForCard.setSkillsRequired(jobs.getSkillsRequired());
        jobDtoForCard.setStatus(jobs.getStatus());

        // Handle User and Profile mapping
        User user = jobs.getUser();
        if (user != null) {
            Profile profile = user.getProfile();
            if (profile != null) {
                // Set location from Profile entity
                jobDtoForCard.setLocation(profile.getLocation());

                // Set client name as "FirstName LastName"
                String firstName = profile.getFirstName() != null ? profile.getFirstName() : "";
                String lastName = profile.getLastName() != null ? profile.getLastName() : "";
                jobDtoForCard.setClientName(firstName + " " + lastName);
            }
        }

        // Set proposal count
        jobDtoForCard.setProposalsCount(jobs.getProposals() != null ? jobs.getProposals().size() : 0);

        return jobDtoForCard;
    }






    public Jobs MapToJobs(JobDtoForCard jobDtoForCard){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Jobs jobs = new Jobs();
        jobs = new ModelMapper().map(jobDtoForCard,Jobs.class);
        return jobs;
    }


}
