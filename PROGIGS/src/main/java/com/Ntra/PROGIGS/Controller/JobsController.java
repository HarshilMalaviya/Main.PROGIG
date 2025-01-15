package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobsController {
    private final JobService jobService;

    @PostMapping("/addjobs")
    public Jobs addjobs(@RequestBody JobDto jobs){
        return   jobService.saveJob(jobs);
    }
    @GetMapping("/Jobs")
    public ResponseEntity<List<JobDto>> getAllJobs()
    {   List<JobDto> list = jobService.getAllJobs();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/job_id/{id}")
    public ResponseEntity<JobDto> findbyJobId(@PathVariable int id) {
        JobDto jobDto=jobService.getJobBYID(id);
        if(jobDto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobDto));
        }
    }
    @GetMapping("/job/skill/{skill}")
    public ResponseEntity<List<JobDto>> findJobbySkills(@PathVariable String skill) {
        List<JobDto> jobs=jobService.getJobByskillRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    @GetMapping("/job/skills")
    public ResponseEntity<List<JobDto>> findJobBySkills(@RequestParam(value = "skill") List<String> skill) {
        List<JobDto> jobs=jobService.getJobBySkillsRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }

    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<Void> deleteJobs(@PathVariable int id){
        jobService.deletebyid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
