package com.Ntra.PROGIGS.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Review")
@AllArgsConstructor
@NoArgsConstructor
public class    Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private String reviewerName;
    private String review;


}
