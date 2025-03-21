package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDate;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // ✅ Ignores unknown fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoAuth {
    private LocalDate joiningDate;
    private String username;
    private String password;
    private Profile profile;
    private UserRole role;

}
