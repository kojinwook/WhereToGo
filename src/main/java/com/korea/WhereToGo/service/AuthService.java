package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.auth.*;
import com.korea.WhereToGo.dto.response.auth.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<? super userIdCheckResponseDto> userIdCheck(userIdCheckRequestDto dto);
    ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck(NicknameCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
    ResponseEntity<? super AdminSignUpResponseDto> adminSignUp(AdminSignUpRequestDto dto);
    ResponseEntity<? super AdminSignInResponseDto> adminSignIn(AdminSignInRequestDto dto);

}
