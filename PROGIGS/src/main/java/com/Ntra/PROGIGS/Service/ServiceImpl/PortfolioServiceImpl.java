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
    @Override
    public PortfolioDto addPortfolio(PortfolioDto portfolio) {
        try {
            User user = getAuthenticatedUser.getAuthenticatedUser();

            portfolio.setProfile(user.getProfile());
            portfolioRepo.save(mapper.MaptoPortfolio(portfolio));
            return portfolio;
        }
        catch (Exception e){
            throw new RuntimeException("Failed to add portfolio");
        }
    }

    @Override
    public PortfolioDto editePortfolio(PortfolioDto portfolio,int id) {
        try {
            PortfolioDto portfolio1 = mapper.MaptoPortfolioDto(portfolioRepo.findById(id).get());
            portfolio1.setPortfolioTitle(portfolio.getPortfolioTitle());
            portfolio1.setSkills(portfolio.getSkills());
            portfolio1.setDescription(portfolio.getDescription());
            portfolioRepo.save(mapper.MaptoPortfolio(portfolio1));
            return portfolio1;
        }
        catch (Exception e){
            throw new RuntimeException("Failed to edite portfolio");
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
