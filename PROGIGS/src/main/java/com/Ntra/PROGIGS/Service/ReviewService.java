package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.ReviewDto;
import com.Ntra.PROGIGS.Entity.Review;

public interface ReviewService {
    public void saveReview(ReviewDto review, int contractId);
}
