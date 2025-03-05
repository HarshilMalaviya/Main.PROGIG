package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import com.Ntra.PROGIGS.Entity.Portfolio;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PortfolioService {
    public Portfolio addPortfolio(Portfolio portfolio);


    Portfolio editePortfolio(Portfolio portfolio, int id);

    void deletePortfolio(int id);

    public Map savePortfolioImage(MultipartFile file,int id);

}
