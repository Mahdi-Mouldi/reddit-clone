package com.pm.reddit_project.controller;


import com.pm.reddit_project.dto.RegisterRequest;
import com.pm.reddit_project.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("User registrated successfully");
    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated successfully", OK);
    }
}
