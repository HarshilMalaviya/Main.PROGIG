package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.LoginDTO;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.DTOs.UserDtoAuth;
import com.Ntra.PROGIGS.Entity.AuthenticationResponse;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Exception.UserAlreadyExistsException;
import com.Ntra.PROGIGS.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        Optional<User> user=userRepo.findByUsername(request.getUsername());

        String token = jwtService.generateToken(user.orElse(null));
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse register(UserDtoAuth request) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepo.findByUsername(request.getUsername());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("StakHolder already exists with username: " + request.getUsername());
        }
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepo.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }


}

