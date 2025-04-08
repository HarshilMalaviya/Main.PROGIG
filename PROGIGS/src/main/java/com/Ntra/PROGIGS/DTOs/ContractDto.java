package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.ContractStatus;
import com.Ntra.PROGIGS.Entity.Jobs;
import com.Ntra.PROGIGS.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // ✅ Ignores unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDto {
    private int id;
    private Date startDate;
    private Date endDate;
    private long amount;
    private ContractStatus status;
    private String jobsTitle;
    private String jobsDescription;

    private String freelancerName;

    private String clientName;
    @JsonIgnore
    private Jobs jobs;
    @JsonIgnore
    private User freelancer;
    @JsonIgnore
    private User client;
}
