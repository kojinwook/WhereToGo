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

    @PatchMapping("/change-password")
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(
            @RequestBody @Valid ChangePasswordRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
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

    @GetMapping("/user-list")
    public ResponseEntity<? super GetUserListResponseDto> getUserList(
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(userId);
        return response;
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<? super WithdrawalUserResponseDto> withdrawalUser(
            @RequestBody @Valid WithdrawalUserRequestDto requestBody
    ) {
        ResponseEntity<? super WithdrawalUserResponseDto> response = userService.withdrawalUser(requestBody);
        return response;
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(
            @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super DeleteUserResponseDto> response = userService.deleteUser(userId);
        return response;
    }

    @PostMapping("/recovery-password")
    public ResponseEntity<? super PasswordRecoveryResponseDto> passwordRecovery(
            @RequestBody @Valid PasswordRecoveryRequestDto dto
    ) {
        ResponseEntity<? super PasswordRecoveryResponseDto> response = userService.passwordRecovery(dto.getEmail());
        return response;
    }

    @PostMapping("/find-userId")
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(
            @RequestBody @Valid FindUserIdRequestDto dto
    ) {
        ResponseEntity<? super FindUserIdResponseDto> response = userService.findUserId(dto.getEmail());
        return response;
    }

    @PostMapping("/report-user/{userId}")
    public ResponseEntity<? super PostReportUserResponseDto> reportUser(
            @RequestBody @Valid PostReportUserRequestDto dto,
            @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super PostReportUserResponseDto> response = userService.reportUser(dto, userId);
        return response;
    }

    @PostMapping("/block-user")
    public ResponseEntity<? super BlockUserResponseDto> blockUser(
            @RequestBody @Valid BlockUserRequestDto dto,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super BlockUserResponseDto> response = userService.blockUser(dto, userId);
        return response;
    }

    @GetMapping("/temperature-top5")
    public ResponseEntity<? super GetTop5TemperatureUserResponseDto> getTop5User() {
        ResponseEntity<? super GetTop5TemperatureUserResponseDto> response = userService.getTop5User();
        return response;
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
            @RequestBody @Valid PatchProfileImageRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestBody, userId);
        return response;
    }

    @PatchMapping("/patch-user")
    public ResponseEntity<? super PatchUserResponseDto> patchUser(
            @RequestBody @Valid PatchUserRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchUserResponseDto> response = userService.patchUser(requestBody, userId);
        return response;
    }

    @PostMapping("/verify-password")
    public ResponseEntity<? super VerifyPasswordResponseDto> verifyPassword(
            @RequestBody @Valid VerifyPasswordRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super VerifyPasswordResponseDto> response = userService.verifyPassword(requestBody, userId);
        return response;
    }
}
