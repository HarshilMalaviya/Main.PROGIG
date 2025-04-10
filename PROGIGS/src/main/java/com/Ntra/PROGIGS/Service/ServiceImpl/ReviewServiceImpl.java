package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ReviewDto;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Entity.ContractStatus;
import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.ReviewMapper;
import com.Ntra.PROGIGS.Repository.ContractRepo;
import com.Ntra.PROGIGS.Repository.ReviewRepo;
import com.Ntra.PROGIGS.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ContractRepo contractRepo;

    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;

    @Autowired
    private ReviewMapper reviewMapper;


    @Override
    public void saveReview(ReviewDto reviewDto) {
        User user = getAuthenticatedUser.getAuthenticatedUser();
        Contract contract = contractRepo.findById(reviewDto.getContractId()).orElseThrow(()->new RuntimeException("Contract not found"));
        Review review = reviewMapper.MapptoReview(reviewDto);
        review.setJobs(contract.getJobs());
        if(user.getRole().toString().equals("CLIENT")) {
            review.setUser(contract.getFreelancer());
        } else if (user.getRole().toString().equals("FREELANCER")) {
            review.setUser(contract.getClient());

        }
        review.setReviewerName(user.getUsername());
        reviewRepo.save(review);
    }
}
