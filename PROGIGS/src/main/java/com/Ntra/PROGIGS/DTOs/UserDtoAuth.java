package com.Ntra.PROGIGS.DTOs;

import com.Ntra.PROGIGS.Entity.UserRole;
import lombok.Data;

import java.util.List;
@Data
public class UserDtoAuth {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private UserRole role;

}
