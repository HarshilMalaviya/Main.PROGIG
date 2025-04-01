package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.EducationDto;
import com.Ntra.PROGIGS.Entity.Education;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.EducationMapper;
import com.Ntra.PROGIGS.Repository.EducationRepo;
import com.Ntra.PROGIGS.Service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepo educationRepo;

    @Autowired
    private EducationMapper educationMapper;

    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;


    @Override
    public EducationDto addEducation(EducationDto education) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        education.setProfile(user.getProfile());
        educationRepo.save(educationMapper.MapToEducation(education));
        return education;
    }

    @Override
    public void deleteEducation(int id) {
        educationRepo.deleteById(id);
    }
}
