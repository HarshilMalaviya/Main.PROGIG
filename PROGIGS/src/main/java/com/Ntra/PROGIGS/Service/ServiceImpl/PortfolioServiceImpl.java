package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Filter.GetAuthenticatedUser;
import com.Ntra.PROGIGS.Mapper.PortfolioMapper;
import com.Ntra.PROGIGS.Repository.PortfolioRepo;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
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
    private ProfileRepo profileRepo;
    @Autowired
   private Cloudinary cloudinary;
    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;
//    @Override
//    public PortfolioDto addPortfolio(MultipartFile file, PortfolioDto portfolio) {
//        try {
//
//            User user = getAuthenticatedUser.getAuthenticatedUser();
//
//            portfolio.setProfile(user.getProfile());
//            portfolioRepo.save(mapper.MaptoPortfolio(portfolio));
//            return portfolio;
//        }
//        catch (Exception e){
//            throw new RuntimeException("Failed to add portfolio");
//        }
//    }

    @Override
    public PortfolioDto addPortfolio(MultipartFile file, PortfolioDto portfolioDto) {
        try {
            User user = getAuthenticatedUser.getAuthenticatedUser();
            Portfolio portfolio = mapper.MaptoPortfolio(portfolioDto);
            portfolio.setProfile(user.getProfile());

            // If file is provided, upload it
            if (file != null && !file.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
                portfolio.setPortfolioImage(uploadResult.get("url").toString());
            }

            // Save portfolio
            Portfolio savedPortfolio = portfolioRepo.save(portfolio);

            // Convert entity back to DTO
            return mapper.MaptoPortfolioDto(savedPortfolio);

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Failed to add portfolio: " + e.getMessage());
        }
    }





    @Override
    public void deletePortfolio(int id) {
        try {
            portfolioRepo.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to delete portfolio");
        }

    }




}
