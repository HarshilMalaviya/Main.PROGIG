package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.Jobs;

import java.util.List;

public interface JobService {
    public Jobs saveJob(JobDto jobs);
    public List<JobDto> getAllJobs ();
    public JobDto getJobBYID(int id);

    List<JobDto> getJobBySkillsRequired(List<String> skill);

    List<JobDto> getJobByskillRequired(String skill);

    public void deletebyid(int id);
}
