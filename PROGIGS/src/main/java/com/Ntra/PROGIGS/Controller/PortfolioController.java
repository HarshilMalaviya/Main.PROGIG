package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.PortfolioDto;
import com.Ntra.PROGIGS.Service.PortfolioService;
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
    public PortfolioDto addPortfolio(@RequestBody PortfolioDto portfolio) {
        return portfolioService.addPortfolio(portfolio);
    }
    @PostMapping("/upload/{id}")
    public Map uploadPortfolioImage(@RequestParam("file") MultipartFile file, @PathVariable int id) {
        final Map data = portfolioService.savePortfolioImage(file, id);
        return data;
    }
    @PutMapping("/{id}")
    public PortfolioDto editePortfolio(@RequestBody PortfolioDto portfolio,@PathVariable int id){
         return portfolioService.editPortfolio(portfolio,id);
    }
    @PutMapping("/editImage/{id}")
    public Map editePortfolio(@RequestParam("file")MultipartFile file,@PathVariable int id){
        return portfolioService.savePortfolioImage(file,id);
    }
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable int id){
        portfolioService.deletePortfolio(id);
    }

}
