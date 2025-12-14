package com.sweetshop.controller;

import com.sweetshop.config.JwtUtil;
import com.sweetshop.dto.auth.LoginRequestDTO;
import com.sweetshop.dto.auth.LoginResponseDTO;
import com.sweetshop.dto.auth.RegisterRequestDTO;
import com.sweetshop.dto.auth.RegisterResponseDTO;
import com.sweetshop.model.User;
import com.sweetshop.repository.UserRepository;
import com.sweetshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081") // Allow requests from any origin
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;   // 👈 injected here

    // Constructor Injection (BEST PRACTICE)
    public AuthController(UserService userService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER
    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO request) {
        User user = userService.registerUser(
                request.getUsername(),
                request.getPassword()
        );

        return new RegisterResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 👇 JWT generation using injected JwtUtil
        String token = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole().name()
        );

        return new LoginResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                token
        );
    }
}
