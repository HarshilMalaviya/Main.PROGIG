package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.LoginDTO;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.DTOs.UserDtoAuth;
import com.Ntra.PROGIGS.Entity.AuthenticationResponse;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Exception.UserAlreadyExistsException;
import com.Ntra.PROGIGS.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse authenticate(LoginDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user=userRepo.findByUsername(request.getUsername());

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public String register(UserDtoAuth request) throws UserAlreadyExistsException {
        User existingUser = userRepo.findByUsername(request.getUsername());
        if(existingUser!=null){
            throw new UserAlreadyExistsException("StakHolder already exists with username: " + request.getUsername());
        }
        User user=new User();
        user.setUsername(request.getUsername());
//        user.setProfile(request.getProfile());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setJoiningDate(LocalDate.now());

        if (request.getProfile() == null) {
            request.setProfile(new Profile()); // Avoid NullPointerException
        }
        Profile profile = request.getProfile();

        // Ensure mandatory fields have values
        if (profile.getPhone() == null) {
            profile.setPhone(""); // Default value for phone
        }
        user.setProfile(profile);
        user = userRepo.save(user);
        String token = jwtService.generateToken(user);

        return "Register Successful";
    }


}

