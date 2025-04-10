package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForGet;
import com.Ntra.PROGIGS.DTOs.ProfileDtoForViewCard;
import com.Ntra.PROGIGS.Entity.LocalVariable;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.ProfileMapper;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Repository.ReviewRepo;
import com.Ntra.PROGIGS.Service.ProfileService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProfileRepo repo;

    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public Map uploadImage(MultipartFile file) {

        try {
           final Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
           User user = getAuthenticatedUser.getAuthenticatedUser();
            ProfileDto profile = profileMapper.MapptoProfileDto(user.getProfile());
            profile.setImageUrl(data.get("url").toString());
            repo.save(profileMapper.MapptoProfile(profile));
           return data;
        } catch (IOException e) {
            throw new RuntimeException("Image Uploading Failed !!");
        }
    }

    @Override
    public ProfileDto editeProfile(ProfileDto profileDto) {
        try {
            User user = getAuthenticatedUser.getAuthenticatedUser();
            int id = user.getProfile().getId();

            Profile existingProfile = this.repo.findById(id).get();

            if (profileDto.getFullName() != null && !profileDto.getFullName().trim().isEmpty()) {
                String[] nameParts = profileDto.getFullName().trim().split("\\s+", 2);
                existingProfile.setFirstName(nameParts[0]);
                existingProfile.setLastName(nameParts.length > 1 ? nameParts[1] : "");
            }
            existingProfile.setDescription(profileDto.getDescription());
            existingProfile.setEmail(profileDto.getEmail());
            existingProfile.setPhone(profileDto.getPhone());
            existingProfile.setSkills(profileDto.getSkills());
            existingProfile.setCompanyName(profileDto.getCompanyName());
            existingProfile.setLocation(profileDto.getLocation());
            existingProfile.setHourlyRate(profileDto.getHourlyRate());
            existingProfile.setFieldOfWork(profileDto.getFieldOfWork());
//            profile2.setArticles(profile.getArticles());
            repo.save(existingProfile);
            return profileMapper.MapptoProfileDto(existingProfile);
        }
        catch(Exception e){
            throw new RuntimeException("Profile Updation Failed !!");
        }
    }

    @Override
    public LocalVariable updateUserSuccessRateById(int id) {
        User user = repo.findById(id).get().getUser();

        int totalJobs = user.getJobs().size();
        long completedJobs = user.getJobs().stream()
                .filter(job -> "COMPLETED".equalsIgnoreCase(job.getStatus().name())) // Use .name() for enums
                .count();

        List<Review> reviews = reviewRepo.findByUser(user);

        double totalRating = reviews.stream().mapToDouble(Review::getRating).sum();
        int totalReviews = reviews.size();

        LocalVariable localVariable = new LocalVariable();
        if (reviews.isEmpty()) {
            totalRating = 0.0;
            totalReviews = 0;
        }


        // Ensure floating-point division
        localVariable.setSuccessRate((totalJobs == 0) ? 0 : ((double) completedJobs / totalJobs) * 100);
        localVariable.setCompletedProject((int) completedJobs);
        localVariable.setReviewCount(totalReviews);
        localVariable.setRating((totalRating / totalReviews ));

        return localVariable;
    }

    @Override
    public LocalVariable updateUserSuccessRate() {
        User user = getAuthenticatedUser.getAuthenticatedUser();

        int totalJobs = user.getJobs().size();
        long completedJobs = user.getJobs().stream()
                .filter(job -> "COMPLETED".equalsIgnoreCase(job.getStatus().name())) // Use .name() for enums
                .count();

        List<Review> reviews = reviewRepo.findByUser(user);

        double totalRating = reviews.stream().mapToDouble(Review::getRating).sum();
        int totalReviews = reviews.size();

        LocalVariable localVariable = new LocalVariable();
        if (reviews.isEmpty()) {
            totalRating = 0.0;
            totalReviews = 0;
        }


        // Ensure floating-point division
        localVariable.setSuccessRate((totalJobs == 0) ? 0 : ((double) completedJobs / totalJobs) * 100);
        localVariable.setCompletedProject((int) completedJobs);
        localVariable.setReviewCount(totalReviews);
        localVariable.setRating((totalRating / totalReviews ));

        return localVariable;
    }

    @Override
    public ProfileDtoForGet getProfile() {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        ProfileDtoForGet profile = profileMapper.MapptoProfileDtoForGet(user.getProfile());
        return profile;
    }
    @Override
    public List<ProfileDtoForGet> getUsersByCountry(String country) {
        List<Profile> profiles = repo.findByCountry(country);
        List<ProfileDtoForGet> profileDtoForGets = profiles.stream().map(profileMapper::MapptoProfileDtoForGet).toList();
        return profileDtoForGets;
    }
    @Override
    public ProfileDtoForViewCard getProfileById(int id) {
        Profile profile = repo.findById(id).get();
        ProfileDtoForViewCard profileDtoForGet = profileMapper.MapptoProfileDtoForViewCard(profile);
        return profileDtoForGet;
    }

    @Override
    public String getProfileImage() {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        String image = user.getProfile().getImageUrl();
        return image;
    }

}
