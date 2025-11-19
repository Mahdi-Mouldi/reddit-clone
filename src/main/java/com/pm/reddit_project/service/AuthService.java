package com.pm.reddit_project.service;


import com.pm.reddit_project.dto.RegisterRequest;
import com.pm.reddit_project.exceptions.SpringRedditException;
import com.pm.reddit_project.model.NotificationEmail;
import com.pm.reddit_project.model.User;
import com.pm.reddit_project.model.VerificationToken;
import com.pm.reddit_project.repository.UserRepository;
import com.pm.reddit_project.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail(
                "Please activate your Account",
                user.getUsername(),
                "Thank you for signing up to spring Reddit, " +
                        "Please click on the below url to activate your account: " +
                        "http://localhost:8080/api/auth/accountVerification/" + token
        ));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid token"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    protected void fetchUserAndEnable(VerificationToken verificationToken){
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new SpringRedditException("User not found with this name " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
