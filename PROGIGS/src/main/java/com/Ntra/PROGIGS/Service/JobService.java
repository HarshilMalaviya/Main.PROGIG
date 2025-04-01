package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
import com.Ntra.PROGIGS.Entity.Jobs;

import java.util.List;

public interface JobService {
    public Jobs saveJob(JobDto jobs);
    public List<JobDtoForCard> getAllJobs ();
    public JobDto getJobBYID(int id);
    List<JobDto> getJobBySkillsRequired(List<String> skill);

    List<JobDto> getJobByskillRequired(String skill);

    public JobDto editeJob(JobDto jobs,int id);

    public void deletebyid(int id);

    public List<JobDtoForCard> appliedJobsForFreelancer();

    List<JobDto> myJobs();
//    public List<JobDto> HiredJobsForFreelancer();
}
