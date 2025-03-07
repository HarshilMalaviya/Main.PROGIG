package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CertificateDto {
    private Integer id;
    private String certificateName;
    private String certificateIssuer;
    private String issuedDate;
    @JsonIgnore
    private Profile profile;
}
