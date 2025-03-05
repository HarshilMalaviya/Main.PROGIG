package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Mapper.PortfolioMapper;
import com.Ntra.PROGIGS.Repository.PortfolioRepo;
import com.Ntra.PROGIGS.Service.PortfolioService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepo portfolioRepo;
    @Autowired
    private PortfolioMapper mapper;
    @Autowired
   private Cloudinary cloudinary;
    @Override
    public Portfolio addPortfolio(Portfolio portfolio) {
        return portfolioRepo.save(portfolio);
    }

    @Override
    public Portfolio editePortfolio(Portfolio portfolio,int id) {
        Portfolio portfolio1 = portfolioRepo.findById(id).get();
        portfolio1.setPortfolioTitle(portfolio.getPortfolioTitle());
        portfolio1.setSkills(portfolio.getSkills());
        portfolio1.setDescription(portfolio.getDescription());

        return portfolioRepo.save(portfolio1);
    }



    @Override
    public void deletePortfolio(int id) {
        portfolioRepo.deleteById(id);

    }

    @Override
    public Map savePortfolioImage(MultipartFile file,int id) {
        try {
            final Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            Portfolio portfolio = portfolioRepo.findById(id).get();
            portfolio.setPortfolioImage(data.get("url").toString());
            portfolioRepo.save(portfolio);
            return data;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image");
        }
    }


}
