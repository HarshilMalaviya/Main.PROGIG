package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Service.BankDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankDetails")
@AllArgsConstructor
public class BankDetailsController {
    @Autowired
    private final BankDetailService bankDetailService;

    @PostMapping
    public String saveBankDetail(@RequestBody BankDetailsDTO bankDetailsDTO){
        return bankDetailService.saveBankDetail(bankDetailsDTO).toString();
    }
    @GetMapping
    public List<BankDetailsDTO> getBankDetail(){
        return bankDetailService.getBankDetail();

    }
    @PutMapping
    public BankDetails editBankDetail(@RequestBody BankDetails bankDetails){
        return bankDetailService.editBankDetail(bankDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBankDetail(@PathVariable int id){
        bankDetailService.deleteBankDetail(id);
    }

}
