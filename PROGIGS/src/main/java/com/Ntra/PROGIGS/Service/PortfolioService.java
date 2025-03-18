package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PortfolioService {
    public PortfolioDto addPortfolio(MultipartFile file, PortfolioDto portfolio);



    public void deletePortfolio(int id);




}
