package com.rms.extranet.service;

import com.rms.extranet.model.LoginRequest;
import com.rms.extranet.model.LoginResponse;
import com.rms.extranet.model.LogoutRequest;
import com.rms.extranet.model.LogoutResponse;
import com.rms.extranet.util.AES256Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    @Autowired
    private RestTemplate restTemplate;

    private final String LOGIN_URL = "https://www.connect2nse.com/extranet-api/login/2.0";
    private final String LOGOUT_URL = "https://www.connect2nse.com/extranet-api/logout/2.0";

    // Your secret key provided during registration
    private final String encryptionKey = "D2QQIxVMNFz5CaajIugLwo+W+q8ABBxa+/KJpvXmXpQ=";

    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        // Encrypt the password
        String encryptedPassword = AES256Encryptor.encryptPassword(loginRequest.getPassword(), encryptionKey);
        loginRequest.setPassword(encryptedPassword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                LOGIN_URL,
                HttpMethod.POST,
                requestEntity,
                LoginResponse.class
        );

        return response.getBody();
    }

    public LogoutResponse logout(String authorizationToken, LogoutRequest logoutRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationToken);

        HttpEntity<LogoutRequest> requestEntity = new HttpEntity<>(logoutRequest, headers);

        ResponseEntity<LogoutResponse> response = restTemplate.exchange(
                LOGOUT_URL,
                HttpMethod.POST,
                requestEntity,
                LogoutResponse.class
        );

        return response.getBody();
    }
}
