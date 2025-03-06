package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Mapper.JobMapper;
import com.Ntra.PROGIGS.Repository.JobRepo;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.JobService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private JobMapper jobMapper;


    @Override
    public Jobs saveJob(JobDto jobs) {
        User user = getAuthenticatedUser();
       Jobs job= jobMapper.MapToJob(jobs);
       job.setUser(user);
        return jobRepo.save(job);
    }
    public List<JobDto> getAllJobs (){
        List<Jobs> jobs = jobRepo.findAll();
        return jobs.stream().map(jobMapper::MapToDto).toList();
    }


    public JobDto getJobBYID(int id){

        Jobs jobs =this.jobRepo.findById(id);
        return this.jobMapper.MapToDto(jobs);
    }

    public List<JobDto> getJobByskillRequired(String skills){
        List<Jobs> jobs = this.jobRepo.findBySkillsRequired(skills);
        return jobs.stream().map(jobMapper::MapToDto).toList();
    }

    public List<JobDto> getJobBySkillsRequired(List<String> skills){
        List<Jobs> jobs = this.jobRepo.findBySkillsRequiredIn(skills);
        List<JobDto> jobDtos = jobs.stream().map(jobMapper::MapToDto).toList();
        return jobDtos;
    }

    @Override
    public JobDto editeJob(JobDto jobs, int id) {
        Jobs jobs1=jobRepo.findById(id);
        jobs1.setTitle(jobs.getTitle());
        jobs1.setDescription(jobs.getDescription());
        jobs1.setSkillsRequired(jobs.getSkillsRequired());
        jobs1.setDuration(jobs.getDuration());
        jobs1.setAmount(jobs.getAmount());
        jobs1.setPayout_methods(jobs.getPayout_methods());
        jobs1.setModules(jobs.getModules());
        return jobMapper.MapToDto(jobRepo.save(jobs1));
    }
    public void deletebyid(int id) {
        jobRepo.deleteById(id);
    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepo.findByUsername(username);
        }
        throw new RuntimeException("User is not authenticated");
    }


}
