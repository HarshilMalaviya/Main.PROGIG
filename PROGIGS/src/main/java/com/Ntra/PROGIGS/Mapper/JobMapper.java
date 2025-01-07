package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.JobDto;

import com.Ntra.PROGIGS.Entity.Jobs;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    @Autowired
    public ModelMapper modelMapper;

    private JobDto MapToDto(Jobs jobs){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        JobDto jobDto = new JobDto();
        jobDto = new ModelMapper().map(jobs,JobDto.class);
        return jobDto;
    }

    private Jobs MapToJob(JobDto jobDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Jobs user = new Jobs();
        user = new ModelMapper().map(jobDto,Jobs.class);
        return user;
    }


}
