package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import com.Ntra.PROGIGS.Entity.Portfolio;
import com.Ntra.PROGIGS.Service.PortfolioService;
import com.Ntra.PROGIGS.Service.ServiceImpl.PortfolioServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    @Autowired
    private final PortfolioService portfolioService;

    @PostMapping
    public Portfolio addPortfolio(@RequestBody Portfolio portfolio) {
        return portfolioService.addPortfolio(portfolio);
    }
    @PostMapping("/upload/{id}")
    public Map uploadPortfolioImage(@RequestParam("file") MultipartFile file, @PathVariable int id) {
        final Map data = portfolioService.savePortfolioImage(file, id);
        return data;
    }
    @PutMapping("/{id}")
    public Portfolio editePortfolio(@RequestBody Portfolio portfolio,@PathVariable int id){
         return portfolioService.editePortfolio(portfolio,id);
    }
    @PutMapping("/editeImage/{id}")
    public Map editePortfolio(@RequestParam("file")MultipartFile file,@PathVariable int id){
        return portfolioService.savePortfolioImage(file,id);
    }
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable int id){
        portfolioService.deletePortfolio(id);
    }
}
