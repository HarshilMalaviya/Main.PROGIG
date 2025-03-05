package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.Entity.Portfolio;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PortfolioService {
    public Portfolio addPortfolio(Portfolio portfolio);
    public  Portfolio editePortfolio( MultipartFile file,Portfolio portfolio,int id);
    void deletePortfolio(int id);

    public Map savePortfolioImage(MultipartFile file,int id);
}
