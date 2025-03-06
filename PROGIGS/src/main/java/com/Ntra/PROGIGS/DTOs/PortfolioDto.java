package com.Ntra.PROGIGS.DTOs;

import lombok.Data;

import java.util.List;
@Data
public class PortfolioDto {
    private String portfolioName;
    private List<String> Skills;
    private String description;

    private String portfolioImage;
}
