package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.ReviewDto;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.Review;
import com.Ntra.PROGIGS.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewMapper {
    @Autowired
    private ModelMapper modelMapper;
    private ReviewDto MapptoReviewDto(Review review) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ReviewDto reviewDto = new ReviewDto();
        reviewDto = new ModelMapper().map(review, ReviewDto.class);
        return reviewDto;
    }
    private Review MapptoReview(ReviewDto reviewDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Review review = new Review();
        review = new ModelMapper().map(reviewDto, Review.class);
        return review;
    }
}
