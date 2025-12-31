package ru.slavikhom.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.slavikhom.userservice.dao.UserRepository;
import ru.slavikhom.userservice.dto.SigninRequest;
import ru.slavikhom.userservice.dto.SignupRequest;
import ru.slavikhom.userservice.model.User;
import ru.slavikhom.userservice.security.jwt.JwtCore;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    public void register(SignupRequest signupRequest) {
        if (userRepository.existsByHandle(signupRequest.getHandle())) {
            throw new IllegalArgumentException("Handle is already in use");
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        User user = new User();
        user.setHandle(signupRequest.getHandle());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
    }

    public String login(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinRequest.getHandle(),
                        signinRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtCore.generateToken(authentication);
    }
}
