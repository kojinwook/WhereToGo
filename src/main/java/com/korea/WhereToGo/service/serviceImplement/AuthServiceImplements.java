package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.auth.*;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.auth.*;
import com.korea.WhereToGo.entity.CertificationEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.provider.EmailProvider;
import com.korea.WhereToGo.provider.JwtProvider;
import com.korea.WhereToGo.repository.CertificationRepository;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImplements implements AuthService {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final JwtProvider jwtProvider;
    private final EmailProvider emailProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super userIdCheckResponseDto> userIdCheck(userIdCheckRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return userIdCheckResponseDto.duplicateId();

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return userIdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super NicknameCheckResponseDto> nicknameCheck(NicknameCheckRequestDto dto) {
        try{
            String nickname = dto.getNickname();
            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if(isExistNickname) return NicknameCheckResponseDto.duplicateNickname();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return NicknameCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto){
        try{
            String userId = dto.getUserId();
            String email = dto.getEmail();

            boolean isExistId = userRepository.existsByEmail(email);
            if (isExistId) return EmailCertificationResponseDto.duplicatedEmail();

            String certificationNumber = getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if(!isSuccessed) return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(userId, email, certificationNumber);
            certificationRepository.save(certificationEntity);
        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return EmailCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto){
        try{
            String userId = dto.getUserId();
            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();

            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            if(certificationEntity == null) return CheckCertificationResponseDto.certificationFail();

            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return CheckCertificationResponseDto.certificationFail();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try{
            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if(isExistId) return SignUpResponseDto.duplicatedEmail();

            String email = dto.getEmail();
            boolean isExistedEmail = userRepository.existsByEmail(email);
            if(isExistedEmail) return SignUpResponseDto.duplicatedEmail();

            String nickname = dto.getNickname();
            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if (isExistNickname) return SignUpResponseDto.duplicatedNickname();

            String certificationNumber = dto.getCertificationNumber();
            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return  SignUpResponseDto.certificationFail();

            boolean agreedPersonal = dto.getAgreedPersonal();
            if(!agreedPersonal) return SignUpResponseDto.disAgreed();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            certificationRepository.deleteByUserId(userId);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        boolean infiniteExpiration = false;
        try{
            String userId = dto.getUserId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return SignInResponseDto.SignInFail();

            System.out.println(userEntity.isBlocked());
            System.out.println(userEntity.getBlockReleaseDate());
            System.out.println(LocalDate.now());
            if (userEntity.isBlocked()) {
                if (userEntity.getBlockReleaseDate() != null && LocalDate.now().isAfter(userEntity.getBlockReleaseDate())) {
                    userEntity.setBlocked(false);
                    userEntity.setBlockReleaseDate(null);
                    userRepository.save(userEntity);
                } else {
                    return SignInResponseDto.UserBlocked(userEntity.getBlockReleaseDate());
                }
            }

            if (userEntity.getRole().equals("ROLE_ADMIN")) {
                infiniteExpiration = true;
            }

            String password = dto.getPassword();
            String encodePassword = userEntity.getPassword();

            boolean isMatched = passwordEncoder.matches(password, encodePassword);
            if(!isMatched) return SignInResponseDto.SignInFail();

            token = jwtProvider.create(userId);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(token, infiniteExpiration);
    }

    @Override
    public ResponseEntity<? super AdminSignUpResponseDto> adminSignUp(AdminSignUpRequestDto dto) {
        try{
            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId) return AdminSignUpResponseDto.duplicateId();

            String email = dto.getEmail();
            boolean isExistedEmail = userRepository.existsByEmail(email);
            if (isExistedEmail) return AdminSignUpResponseDto.duplicatedEmail();

            String nickname = dto.getNickname();
            boolean isExistNickname = userRepository.existsByNickname(nickname);
            if(isExistNickname) return AdminSignUpResponseDto.duplicatedNickname();

            String certificationNumber = dto.getCertificationNumber();
            CertificationEntity certificationEntity = certificationRepository.findByUserId(userId);
            boolean isMatched = certificationEntity.getEmail().equals(email) && certificationEntity.getCertificationNumber().equals(certificationNumber);
            if(!isMatched) return AdminSignUpResponseDto.certificationFail();

            String secretKey = dto.getSecretKey();
            if(!secretKey.equals("1234")) return AdminSignUpResponseDto.noPermission();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            certificationRepository.deleteByUserId(userId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return AdminSignUpResponseDto.success();
    }

    @Override
public ResponseEntity<? super AdminSignInResponseDto> adminSignIn(AdminSignInRequestDto dto) {
        String token = null;
        try{
            String userId = dto.getUserId();
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return AdminSignInResponseDto.SignInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return AdminSignInResponseDto.SignInFail();

            String secretKey = dto.getSecretKey();
            if(!secretKey.equals("1234")) return AdminSignInResponseDto.noPermission();

            token = jwtProvider.create(userId);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return AdminSignInResponseDto.success(token);
    }

    private String getCertificationNumber() {
        String certificationNumber = "";
        for(int count = 0; count < 4; count++) certificationNumber += (int) (Math.random() * 10);
        return certificationNumber;
    }

}
