package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ReviewDto {

    private int reviewId;
    private String reviewerName;
    private String description;
    private double review;
    @JsonIgnore
    private Jobs jobs;
    @JsonIgnore
    private User user;
}
