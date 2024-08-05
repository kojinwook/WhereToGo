package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.user.*;
import com.korea.WhereToGo.dto.response.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<? super GetUserResponseDto> getUser(String userId);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String userId);
    ResponseEntity<? super ChangePasswordResponseDto> changePassword(ChangePasswordRequestDto dto, String userId);
    ResponseEntity<? super WithdrawalUserResponseDto> withdrawalUser(WithdrawalUserRequestDto dto);
    ResponseEntity<? super PasswordRecoveryResponseDto> passwordRecovery(String email);
    ResponseEntity<? super FindUserIdResponseDto> findUserId(String email);
    ResponseEntity<? super GetUserListResponseDto> getUserList(String userId);
    ResponseEntity<? super DeleteUserResponseDto> deleteUser(String userId);
    ResponseEntity<? super PostReportUserResponseDto> reportUser(PostReportUserRequestDto dto, String userId);
    ResponseEntity<? super BlockUserResponseDto> blockUser(BlockUserRequestDto dto, String userId);
    ResponseEntity<? super UnBlockUserResponseDto> unBlockUser(UnBlockUserRequestDto dto, String userId);
    ResponseEntity<? super GetTop3TemperatureUserResponseDto> getTop3User();
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String userId);
    ResponseEntity<? super PatchUserResponseDto> patchUser(PatchUserRequestDto dto, String userId);
    ResponseEntity<? super VerifyPasswordResponseDto> verifyPassword(VerifyPasswordRequestDto dto, String userId);
    ResponseEntity<? super GetReportListResponseDto> getReportList(String nickname, String userId);
    ResponseEntity<? super LikeUserResponseDto> likeUser(String nickname, Long meetingId, String userId);
    ResponseEntity<? super DislikeUserResponseDto> dislikeUser(String nickname, Long meetingId, String userId);
}
