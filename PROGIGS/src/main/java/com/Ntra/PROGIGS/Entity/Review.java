package com.Ntra.PROGIGS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private String reviewerName;
    private String review;
    private double rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore // Prevents infinite recursion
    private Jobs jobs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Prevents deep nesting
    private User user;
}
