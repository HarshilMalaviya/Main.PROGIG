package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.ProposalsDto;
import com.Ntra.PROGIGS.DTOs.ProposalsDtoForGet;
import com.Ntra.PROGIGS.Entity.Proposals;
import com.Ntra.PROGIGS.Service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposals")
public class ProposalsController {
    @Autowired
    private ProposalService proposalService;

    @PostMapping("/{jobid}")
    public ResponseEntity<Proposals> saveProposal(@RequestBody ProposalsDto proposals,@PathVariable int jobid) {
        return ResponseEntity.ok(proposalService.saveProposal(proposals,jobid));
    }
    @GetMapping("/{jobid}")
    public ResponseEntity<List<ProposalsDtoForGet>> getAllProposalFromJob(@PathVariable int jobid) {

        return ResponseEntity.ok( proposalService.getProposalFromJob(jobid));
    }
//    @PutMapping("/{proposalid}")
//    public ResponseEntity<String> changeStatus(@PathVariable int proposalid,@RequestBody ProposalsDto proposals) {
//        proposalService.changeStatus(proposalid,proposals);
//        return ResponseEntity.ok("Status Succesfully Updated");
//    }
}
