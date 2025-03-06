package com.Ntra.PROGIGS.Entity;

import com.Ntra.PROGIGS.DTOs.LoginDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "User")
public class User extends LoginDTO implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    private String username;
    private LocalDate joiningDate;
    @JsonIgnore // Prevent exposing password in API responses
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "freelancer_id",referencedColumnName = "Id")
    @OrderBy("id DESC") // Newest proposals first
    @JsonIgnore
    private List<Proposals> proposals;

    @OneToMany(mappedBy ="user" ,cascade = CascadeType.ALL)
    @OrderBy("id DESC") // Newest jobs first
    @JsonIgnore
    private List<Jobs> jobs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("id DESC") // Newest reviews first
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "freelancer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Contract> freelancerContracts;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
