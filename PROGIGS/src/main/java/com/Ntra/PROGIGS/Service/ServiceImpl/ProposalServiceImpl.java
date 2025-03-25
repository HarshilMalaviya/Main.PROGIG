package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.ProposalsDto;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.Proposals;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Mapper.ProposalMapper;
import com.Ntra.PROGIGS.Repository.JobRepo;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Repository.ProposalsRepo;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProposalServiceImpl implements ProposalService {
    @Autowired
    private ProposalsRepo proposalsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProfileRepo profileRepo;
    @Autowired
    private JobRepo jobRepo;
    @Autowired
    private ProposalMapper proposalsMapper;
    @Override
    public Proposals saveProposal(ProposalsDto proposals,int jobid) {
        User user = getAuthenticatedUser();
        Profile profile=user.getProfile();
        proposals.setUser(user);
        proposals.setFreelancerName(user.getUsername());
        proposals.setFreelancerEmail(profile.getEmail());
//        Jobs jobs = jobRepo.findById(jobid);
//        proposals.setJobs(jobs);
        try {
            Jobs jobs = jobRepo.findById(jobid);
            proposals.setJobTitle(jobs.getTitle());
            proposals.setJobs(jobs);
            User user1 = jobs.getUser();
            proposals.setClientName(user1.getUsername());
            return proposalsRepo.save(proposalsMapper.MapToProposal(proposals));
        }catch (Exception e){
            throw new RuntimeException ("Job dont exist!!");
        }

    }

    @Override
    public List<Proposals> getProposalFromJob(int jobid) {

        return proposalsRepo.findAllProposalsByJobs(jobid);
    }


    @Override
    public void changeStatus(int proposalid, ProposalsDto proposals) {
        Proposals proposals1 = proposalsRepo.findById(proposalid).get();
        proposals1.setStatus(proposals.getStatus());
        proposalsRepo.save(proposals1);
    }

    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepo.findByUsername(username);
        }
        throw new RuntimeException("User is not authenticated");
    }
}
