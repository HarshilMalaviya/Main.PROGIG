package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
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

    @PostMapping
    public Jobs addjobs(@RequestBody JobDto jobs){
        return   jobService.saveJob(jobs);
    }
    @GetMapping
    public ResponseEntity<List<JobDtoForCard>> getAllJobs()
    {   List<JobDtoForCard> list = jobService.getAllJobs();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else{
            return ResponseEntity.of(Optional.of(list));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobDto> findbyJobId(@PathVariable int id) {
        JobDto jobDto=jobService.getJobBYID(id);
        if(jobDto==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobDto));
        }
    }
    @GetMapping("/appliedJobs")
    public ResponseEntity<List<JobDto>> findAppliedJobs() {
        List<JobDto> jobs=jobService.appliedJobsForFreelancer();
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    /*@GetMapping("/hiredJobs")
    public ResponseEntity<List<JobDto>> hiredJobs() {
        List<JobDto> jobs=jobService.HiredJobsForFreelancer();
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }*/
    @GetMapping("/jobbyskill/{skill}")
    public ResponseEntity<List<JobDto>> findJobbySkills(@PathVariable String skill) {
        List<JobDto> jobs=jobService.getJobByskillRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    @GetMapping("/jobbyskills")
    public ResponseEntity<List<JobDto>> findJobBySkills(@RequestParam(value = "skill") List<String> skill) {
        List<JobDto> jobs=jobService.getJobBySkillsRequired(skill);
        if(jobs==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.of(Optional.of(jobs));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<JobDto> updateJobs(@RequestBody JobDto jobs,@PathVariable int id){
    return ResponseEntity.of(Optional.of(jobService.editeJob(jobs,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobs(@PathVariable int id){
        jobService.deletebyid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
