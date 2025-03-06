package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Proposals;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;

@Data
public class ProposalsDto {
    private Integer id;
    private String jobTitle;
    private String clientName;
    private String freelancerName;
    private String freelancerEmail;
    private String description;
    private Long bid;
    private Date finishingTime;


}
