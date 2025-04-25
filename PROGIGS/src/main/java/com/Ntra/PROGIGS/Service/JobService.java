package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
import com.Ntra.PROGIGS.Entity.Jobs;

import java.util.List;

public interface JobService {
    public JobDto saveJob(JobDto jobs);
    public List<JobDtoForCard> getAllJobs ();
    public List<JobDtoForCard> getFiveJobs ();
    public JobDto getJobBYID(int id);
    List<JobDto> getJobBySkillsRequired(List<String> skill);

    List<JobDto> getJobByskillRequired(String skill);

    public List<JobDtoForCard> findJobByLocation (String country);

    public JobDto editeJob(JobDto jobs, int id);

    public void deletebyid(int id);

    public List<JobDtoForCard> findByCatogory(String catogory);

    public List<JobDtoForCard> appliedJobsForFreelancer();

    List<JobDtoForCard> myJobs();
//    List<JobDtoForCard> jobsByLocation(String location);
//    public List<JobDto> HiredJobsForFreelancer();
}
