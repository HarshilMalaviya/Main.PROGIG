package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
public class ContractController {
    @Autowired
    private ContractService contractService;
    @PostMapping("/{jobid}")
    public ResponseEntity<ContractDto> saveContract(@RequestBody ContractDto contract, @PathVariable int jobid){
        return ResponseEntity.ok(contractService.saveContract(contract,jobid));
    }
    @GetMapping("/{contractid}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable int contractid){
        return ResponseEntity.ok(contractService.getContractById(contractid));
    }
    @PutMapping("/{contractid}")
    public ResponseEntity<String> editeContractStatus(@PathVariable int contractid){
        contractService.editeContractStatus(contractid);
        return ResponseEntity.ok("Contract Closed Successfully");
    }
    @GetMapping("/activeJobs")
    public ResponseEntity<List<JobDto>> activeJobsByContract(){
        return ResponseEntity.ok(contractService.activeContract());
    }
    @GetMapping("/myContract")
    public ResponseEntity<List<ContractDto>> getContract(){
        return ResponseEntity.ok(contractService.getContract());
    }

}
