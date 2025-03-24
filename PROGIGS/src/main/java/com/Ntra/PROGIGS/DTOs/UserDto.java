package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class UserDto{

    private Integer id;
    private String username;
    private LocalDate joiningDate;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    private ProfileDtoForCard profileDtoForCard;
}
