package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PortfolioService {
    public PortfolioDto addPortfolio(PortfolioDto portfolio);


    public PortfolioDto editPortfolio(PortfolioDto portfolio, int id);

    public void deletePortfolio(int id);

    public Map savePortfolioImage(MultipartFile file,int id);



}
