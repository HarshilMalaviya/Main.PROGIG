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
    public void addEducation(EducationDto education) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        education.setProfile(user.getProfile());
        educationRepo.save(educationMapper.MapToEducation(education));

    }

    @Override
    public void editEducation(EducationDto education, int id) {
        Education education1 = educationRepo.findById(id).get();
        education1.setCourse(education.getCourse());
        education1.setInstitute(education.getInstitute());
        education1.setYear(education.getYear());
        educationRepo.save(education1);
    }
}
