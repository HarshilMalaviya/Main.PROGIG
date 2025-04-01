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
@RequestMapping("/profile/bank")
public class BankDetailController {
    @Autowired
    private BankDetailService bankDetailService;

    @PostMapping
    public BankDetailsDTO addBankDetail(@RequestBody BankDetailsDTO bankDetailsDTO) {
        return bankDetailService.saveBankDetail(bankDetailsDTO);
    }

    @PutMapping("/{id}")
    public BankDetailsDTO editeBankDetail(@RequestBody BankDetailsDTO bankDetailsDTO,@PathVariable int id) {
        return bankDetailService.editBankDetail(bankDetailsDTO,id);
    }

    @DeleteMapping
    public void deleteBankDetail(@PathVariable int id) {
        bankDetailService.deleteBankDetail(id);
    }
}
