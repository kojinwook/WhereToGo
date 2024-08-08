package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.auth.*;
import com.korea.WhereToGo.dto.response.auth.*;
import com.korea.WhereToGo.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/oauth-response")
    public void handleOAuthResponse(
            @RequestParam("token") String token, HttpServletResponse response) throws IOException {
        String redirectUrl = "http://3.35.30.191:4040?token=" + token;
        response.sendRedirect(redirectUrl);
    }

    @PostMapping("/userId-check")
    public ResponseEntity<? super userIdCheckResponseDto> userIdCheck(
            @RequestBody @Valid userIdCheckRequestDto requestBody
    ) {
        ResponseEntity<? super userIdCheckResponseDto> response = authService.userIdCheck(requestBody);
        return response;
    }
    @PostMapping("/nickname-check")
    public ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck(
            @RequestBody @Valid NicknameCheckRequestDto requestBody
    ) {
        ResponseEntity<? super NicknameCheckResponseDto> response = authService.nicknameCheck(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody EmailCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/admin-sign-up")
    public ResponseEntity<? super AdminSignUpResponseDto> adminSignUp(
            @RequestBody @Valid AdminSignUpRequestDto requestBody
    ) {
        ResponseEntity<? super AdminSignUpResponseDto> response = authService.adminSignUp(requestBody);
        return response;
    }

    @PostMapping("/admin-sign-in")
    public ResponseEntity<? super AdminSignInResponseDto> adminSignIn(
            @RequestBody @Valid AdminSignInRequestDto requestBody
    ) {
        ResponseEntity<? super AdminSignInResponseDto> response = authService.adminSignIn(requestBody);
        return response;
    }
}
