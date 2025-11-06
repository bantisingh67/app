package com.app.controller;

import com.app.entity.User;
import com.app.payload.JWTTokenDto;
import com.app.payload.LoginDto;
import com.app.repository.UserRepository;
import com.app.service.JWTService;
import com.app.service.OTPService;
import com.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class Authcontroller {
 private PasswordEncoder passwordEncoder;
 private UserRepository userRepository;
 private UserService userService;
 private OTPService otpService;
 private JWTService jwtService;

    public Authcontroller(PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService, OTPService otpService, JWTService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    //signup authentication
    //http://localhost:8035/api/v1/auth/signup
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody User user
    ) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()) {
            return new ResponseEntity<>("username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exits", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String hasPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hasPass);
        user.setRole("USER ROLE");
        userRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    //Content manager signup
    //http://localhost:8035/api/v1/auth/content-manager-signup
    @PostMapping("/content-manager-signup")
    public ResponseEntity<?> createContentManager(
            @RequestBody User user
    ) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()) {
            return new ResponseEntity<>("username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exits", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String getPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(getPass);
        user.setRole("CONTENT-MANAGER-R0LE");
        userRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    //http://localhost:8035/api/v1/auth/signup
    @PostMapping("/blog-manager-signup")
    public ResponseEntity<?> createBlogManager(
            @RequestBody User user
    ) {
        Optional<User> opUsername = userRepository.findByUsername(user.getUsername());
        if(opUsername.isPresent()) {
            return new ResponseEntity<>("username already exists",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmailId(user.getEmailId());
        if(opEmail.isPresent()) {
            return new ResponseEntity<>("Email already exits", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String getPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(getPass);
        user.setRole("USER ROLE");
        userRepository.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    //signIn with jwt token security
    //http://localhost:8035/api/v1/auth/userSignIn
    @PostMapping("/userSignIn")
    public ResponseEntity<?> userSignIn(
           @RequestBody LoginDto dto
    ) {
        String jwtToken = userService.verfiyLogin(dto);
        if(jwtToken != null) {
            JWTTokenDto jwtToken1 = new JWTTokenDto();
            jwtToken1.setToken(jwtToken);
            jwtToken1.setTokenType("jwt");
            return new ResponseEntity<>(jwtToken1, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid Token", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //http://localhost:8035/api/v1/auth/message
 @PostMapping("/message")
 public String getMessage() {
        return "signup security spring";
 }

 //-
    @PostMapping("/otp-login")
    public String generateOtp(@RequestParam String mobile) {
        Optional<User> opUser = userRepository.findByMobile(mobile);
        if(opUser.isPresent()) {
            String otp = otpService.generateOTP(mobile);
            return otp+ "   " +mobile;
        }
        return "User not found";
    }
    //http://localhost:8035/api/v1/auth/validate-otp
    @PostMapping("/validate-otp")
    public String validateOtp(
            @RequestParam String mobile,
            @RequestParam String otp
    ) {
        boolean status = otpService.validateOTP(mobile, otp);
        if (status) {
            //generate jwt token
            Optional<User> opUser = userRepository.findByMobile(mobile);
            if (opUser.isPresent()) {
                String jwtToken = jwtService.generateToken(opUser.get().getUsername());
                return jwtToken;
            }
            }
            return status ? "otp validate successfully" : "Invalid OTP";
        }
}
