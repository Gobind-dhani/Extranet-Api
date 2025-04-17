package com.rms.extranet.controller;

import com.rms.extranet.model.LoginRequest;
import com.rms.extranet.model.LoginResponse;
import com.rms.extranet.model.LogoutRequest;
import com.rms.extranet.model.LogoutResponse;
import com.rms.extranet.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = loginService.login(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationToken,
                                    @RequestBody LogoutRequest logoutRequest) {
        try {
            LogoutResponse logoutResponse = loginService.logout(authorizationToken, logoutRequest);

            if ("success".equalsIgnoreCase(logoutResponse.getStatus())) {
                return ResponseEntity.ok(logoutResponse);
            } else {
                return ResponseEntity.badRequest().body("Logout failed: " + logoutResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }
}
