package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.CertificateDto;
import com.Ntra.PROGIGS.DTOs.EducationDto;
import com.Ntra.PROGIGS.Entity.Certificates;
import com.Ntra.PROGIGS.Entity.Education;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper {
    @Autowired
    private ModelMapper modelMapper;
    public EducationDto MapToDto(Education education){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        EducationDto educationDto = new EducationDto();
        educationDto = new ModelMapper().map(education,EducationDto.class);
        return educationDto;
    }

    public Education MapToEducation(EducationDto educationDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Education education = new Education();
        education = new ModelMapper().map(educationDto,Education.class);
        return education;
    }
}
