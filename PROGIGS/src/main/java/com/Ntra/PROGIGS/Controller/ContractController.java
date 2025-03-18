package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {
    @Autowired
    private ContractService contractService;
    @PostMapping("/{jobid}")
    public ResponseEntity<Contract> saveContract(@RequestBody ContractDto contract, @PathVariable int jobid){
        return ResponseEntity.ok(contractService.saveContract(contract,jobid));
    }
    @GetMapping("/{contractid}")
    public ResponseEntity<Contract> getContractById(@PathVariable int contractid){
        return ResponseEntity.ok(contractService.getContractById(contractid));
    }
    @PutMapping("/{contractid}")
    public ResponseEntity<Contract> editeContractStatus(@RequestBody ContractDto contract,@PathVariable int contractid){
        return ResponseEntity.ok(contractService.editeContractStatus(contract,contractid));
    }

}
