package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
@Data
public class PortfolioDto {
    private int portfolioId;
    private String portfolioTitle;
    private List<String> skills;
    private String description;
    private String portfolioImage;
    @JsonIgnore
    private Profile profile;
}
