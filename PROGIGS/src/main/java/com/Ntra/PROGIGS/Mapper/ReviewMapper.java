package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ReviewDto;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ReviewDto MapptoReviewDto(Review review) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ReviewDto reviewDto = new ReviewDto();
        reviewDto = new ModelMapper().map(review, ReviewDto.class);
        return reviewDto;
    }
    public Review MapptoReview(ReviewDto reviewDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Review review = new Review();
        review.setReviewerName(reviewDto.getReviewerName());
        review.setReview(reviewDto.getReview());
        review.setRating(reviewDto.getRating());
        review.setUser(reviewDto.getUser());
        review.setJobs(reviewDto.getJobs());
        return review;
    }
}
