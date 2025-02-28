package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private List<String> skills;
    private String description;
    private List<UserRole> role;
    private String status;
    private String whyRejected;
    @OneToOne(mappedBy = "User", cascade = CascadeType.ALL)
    private Profile profile;
}
