package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.UserRole;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
public class UserDtoAuth {
    private LocalDate joiningDate;
    private String username;
    private String password;
    private Profile profile;
    private UserRole role;

}
