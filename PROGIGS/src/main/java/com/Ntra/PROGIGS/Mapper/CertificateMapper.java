package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.CertificateDto;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.Certificates;
import com.Ntra.PROGIGS.Entity.Jobs;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateMapper {

    @Autowired
    public ModelMapper modelMapper;

    public CertificateDto MapToDto(Certificates certificates){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        CertificateDto certificateDto = new CertificateDto();
        certificateDto = new ModelMapper().map(certificates,CertificateDto.class);
        return certificateDto;
    }

    public Certificates MapToCertificates(CertificateDto certificateDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Certificates certificates = new Certificates();
        certificates = new ModelMapper().map(certificateDto,Certificates.class);
        return certificates;
    }
}
