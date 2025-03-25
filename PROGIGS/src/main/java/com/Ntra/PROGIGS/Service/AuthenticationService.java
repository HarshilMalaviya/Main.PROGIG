package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.LoginDTO;
import com.Ntra.PROGIGS.DTOs.UserDto;
import com.Ntra.PROGIGS.DTOs.UserDtoAuth;
import com.Ntra.PROGIGS.Entity.AuthenticationResponse;
import com.Ntra.PROGIGS.Entity.Profile;
import com.Ntra.PROGIGS.Entity.User;
import com.Ntra.PROGIGS.Exception.UserAlreadyExistsException;
import com.Ntra.PROGIGS.Repository.ProfileRepo;
import com.Ntra.PROGIGS.Repository.UserRepo;
import com.Ntra.PROGIGS.Service.ServiceImpl.EmailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceImpl emailService;
    private final ProfileRepo profileRepo;
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

//        for otp generation and send the mail
        String otp = generateOTP();
        user.setOtp(otp);
        try {
            sendVerificationEmail(user.getProfile().getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }


        user = userRepo.save(user);
        String token = jwtService.generateToken(user);

        return "Register Successful";
    }
    public String generateOTP() {
        Random random = new Random();
        int otpValue = random.nextInt(900000) + 100000;
        return String.valueOf(otpValue);
    }
    public void sendVerificationEmail(String email, String otp) throws MessagingException {
        String subject = "Email Verification";
        String text = "Your verification code is: " + otp;
        emailService.sendEmail(email, subject, text);
    }

    public void verifyOTP(String email, String otp) {
        Profile profile=profileRepo.findByEmail(email);
        User user=userRepo.findByProfile(profile);

        if(profile==null){
            throw new RuntimeException("Profile not found for email: " + email);
        } else if (profile.isVerified()) {
            throw new RuntimeException("Profile already verified for email: " + email);
        } else if (user.getOtp().equals(otp)) {
            profile.setVerified(true);
            profileRepo.save(profile);
        }else
        {
            throw new RuntimeException("Invalid OTP for email: " + email);
        }

    }

}

