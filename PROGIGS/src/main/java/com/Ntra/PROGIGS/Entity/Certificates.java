package com.Ntra.PROGIGS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "certificates")
public class Certificates {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String certificateName;
    private String certificateIssuer;
    private String issuedDate;


    @ManyToOne
    @JoinColumn(name = "certificate_id")
    @JsonIgnore
    private Profile profile;
}
