package com.Ntra.PROGIGS.Mapper;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

public class PortfolioMapper {
    @Autowired
    private ModelMapper modelMapper;
    private PortfolioDto MapptoPortfolioDto(Portfolio portfolio) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto = new ModelMapper().map(portfolio, PortfolioDto.class);
        return portfolioDto;
    }
    private Portfolio MapptoPortfolio(PortfolioDto portfolioDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Portfolio portfolio = new Portfolio();
        portfolio = new ModelMapper().map(portfolioDto, Portfolio.class);
        return portfolio;
    }
}
