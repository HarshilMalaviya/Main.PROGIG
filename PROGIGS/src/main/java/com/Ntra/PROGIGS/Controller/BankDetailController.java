package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.BankDetailsDTO;
import com.Ntra.PROGIGS.Entity.BankDetails;
import com.Ntra.PROGIGS.Service.BankDetailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bankDetail")
public class BankDetailController {
    @Autowired
    private BankDetailService bankDetailService;

    @PostMapping("/addBankDetail")
    public BankDetailsDTO addBankDetail(@RequestBody BankDetailsDTO bankDetailsDTO) {
        return bankDetailService.saveBankDetail(bankDetailsDTO);
    }

    @PutMapping("/editeBankDetail")
    public BankDetailsDTO editeBankDetail(@RequestBody BankDetailsDTO bankDetailsDTO) {
        return bankDetailService.editBankDetail(bankDetailsDTO);
    }

    @DeleteMapping("/deleteBankDetail")
    public void deleteBankDetail(@PathVariable int id) {
        bankDetailService.deleteBankDetail(id);
    }
}
