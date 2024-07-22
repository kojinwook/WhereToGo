package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.user.*;
import com.korea.WhereToGo.dto.response.user.*;
import com.korea.WhereToGo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSignInUserResponseDto> responseBody = userService.getSignInUser(userId);
        return responseBody;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetUserResponseDto> getUser(
            @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetUserResponseDto> responseBody = userService.getUser(userId);
        return responseBody;
    }

    @PatchMapping("/change-password/{userId}")
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(
            @RequestBody @Valid ChangePasswordRequestDto requestBody,
            @PathVariable("userId") String userId
    ){
        ResponseEntity<? super ChangePasswordResponseDto> response = userService.changePassword(requestBody, userId);
        return response;
    }

    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(
            @RequestBody @Valid PatchNicknameRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestBody, userId);
        return response;
    }

    @DeleteMapping("/withdrawal/{userId}")
    public ResponseEntity<? super WithdrawalUserResponseDto> withdrawalUser(
            @PathVariable("userId") String userId,
            @RequestBody @Valid WithdrawalUserRequestDto requestBody
    ) {
        ResponseEntity<? super WithdrawalUserResponseDto> response = userService.withdrawalUser(requestBody, userId);
        return response;
    }

    @PostMapping("/recovery-password")
    public ResponseEntity<? super PasswordRecoveryResponseDto> passwordRecovery(
            @RequestBody @Valid PasswordRecoveryRequestDto dto
    ) {
        return userService.passwordRecovery(dto.getEmail());
    }

    @PostMapping("/find-userId")
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(
            @RequestBody @Valid FindUserIdRequestDto dto
            ) {
        return userService.findUserId(dto.getEmail());
    }
}
