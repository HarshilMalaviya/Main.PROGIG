package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ProfileDto;
import com.Ntra.PROGIGS.Entity.LocalVariable;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Mapper.ProfileMapper;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Repository.ReviewRepo;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.ProfileService;
import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ProfileRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ReviewRepo reviewRepo;

    @Override
    public Map uploadImage(MultipartFile file, int profileId) {



        try {
           final Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            User user = getAuthenticatedUser();
            ProfileDto profile = profileMapper.MapptoProfileDto(this.repo.findById(profileId).get());
            profile.setImageUrl(data.get("url").toString());
            repo.save(profileMapper.MapptoProfile(profile));
           return data;
        } catch (IOException e) {
            throw new RuntimeException("Image Uploading Failed !!");
        }
    }

    @Override
    public Profile editeProfile(ProfileDto profile, int id) {
        Profile profile2 = this.repo.findById(id).get();
        profile2.setFirstName(profile.getFirstName());
        profile2.setLastName(profile.getLastName());
        profile2.setDescription(profile.getDescription());
        profile2.setEmail(profile.getEmail());
        profile2.setPhone(profile.getPhone());
        profile2.setSkills(profile.getSkills());
        profile2.setCompanyName(profile.getCompanyName());
        profile2.setLocation(profile.getLocation());
        profile2.setHourlyRate(profile.getHourlyRate());
        profile2.setEducation(profile.getEducation());
        profile2.setArticles(profile.getArticles());
        repo.save(profile2);
        return profile2;
    }

    public LocalVariable updateUserSuccessRate() {
        User user = getAuthenticatedUser();

        int totalJobs = user.getJobs().size();
        long completedJobs = user.getJobs().stream()
                .filter(job -> "COMPLETED".equalsIgnoreCase(job.getStatus().name())) // Use .name() for enums
                .count();

        List<Review> reviews = reviewRepo.findByUser(user);

        if (reviews.isEmpty()) {
            throw new RuntimeException("No reviews found for the user.");
        }

        double totalRating = reviews.stream().mapToDouble(Review::getReview).sum();
        int totalReviews = reviews.size();

        LocalVariable localVariable = new LocalVariable();

        // Ensure floating-point division
        localVariable.setSuccessRate((totalJobs == 0) ? 0 : ((double) completedJobs / totalJobs) * 100);
        localVariable.setCompletedProject((int) completedJobs);
        localVariable.setReviewCount(totalReviews);
        localVariable.setRating((totalRating / totalReviews ));

        return localVariable;
    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepo.findByUsername(username);
        }
        throw new RuntimeException("User is not authenticated");
    }
}
