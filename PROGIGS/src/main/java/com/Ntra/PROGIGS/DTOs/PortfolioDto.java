package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // ✅ Ignores unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PortfolioDto {
    private int portfolioId;
    private String portfolioTitle;
    private List<String> skills;
    private String description;
    @JsonIgnore
    private Profile profile;
}
