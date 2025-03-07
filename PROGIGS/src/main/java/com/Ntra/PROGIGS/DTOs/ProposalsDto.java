package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.Proposals;
import com.Ntra.PROGIGS.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // ✅ Ignores unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProposalsDto {
    private Integer id;
    private String jobTitle;
    private String clientName;
    private String freelancerName;
    private String freelancerEmail;
    private String description;
    private Long bid;
    private Date finishingTime;


    private User user;
    private Jobs jobs;
}
