package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ContractDto;
import com.Ntra.PROGIGS.DTOs.JobDto;
import com.Ntra.PROGIGS.DTOs.JobDtoForCard;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Service.ContractService;
import com.Ntra.PROGIGS.Service.ServiceImpl.NotificationServiceImpl;
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

    @Autowired
    private NotificationServiceImpl notificationService;
    @PostMapping("/{proposalid}")
    public ResponseEntity<ContractDto> saveContract(@RequestBody ContractDto contract, @PathVariable int proposalid){
        return ResponseEntity.ok(contractService.saveContract(contract,proposalid));
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
    public ResponseEntity<List<JobDtoForCard>> activeJobsByContract(){
        return ResponseEntity.ok(contractService.activeContract());
    }
    @GetMapping("/myContract")
    public ResponseEntity<List<ContractDto>> getContract(){
        return ResponseEntity.ok(contractService.getContract());
    }

    @PostMapping("/{contractId}/close")
    public String closeContract(@PathVariable Integer contractId, @RequestParam Integer freelancerId) {
        // Your DB logic to close contract here...
        notificationService.notifyFreelancer(freelancerId, contractId);
        return "Contract closed, notification sent.";
    }

}
