package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.LoginDTO;
import com.Ntra.PROGIGS.DTOs.UserDtoAuth;
import com.Ntra.PROGIGS.Entity.AuthenticationResponse;
import com.Ntra.PROGIGS.Exception.UserAlreadyExistsException;
import com.Ntra.PROGIGS.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDtoAuth request) throws UserAlreadyExistsException {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
