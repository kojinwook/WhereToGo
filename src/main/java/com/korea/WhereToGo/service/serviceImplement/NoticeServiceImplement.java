package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.Notice.PatchNoticeRequestDto;
import com.korea.WhereToGo.dto.request.Notice.PostNoticeRequestDto;
import com.korea.WhereToGo.dto.response.Notice.*;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.NoticeEntity;
import com.korea.WhereToGo.entity.QuestionEntity;
import com.korea.WhereToGo.repository.NoticeRepository;
import com.korea.WhereToGo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImplement implements NoticeService {
    private final NoticeRepository noticeRepository;
    @Override
    public ResponseEntity<? super GetNoticeResponseDto> getNotice(Long NoticeId){
        NoticeEntity noticeEntity = null;
        try{
            noticeEntity = noticeRepository.findByNoticeId(NoticeId);
            if(noticeEntity == null) return GetNoticeResponseDto.notExistNotice();
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetNoticeResponseDto.success(noticeEntity);
    }
    @Override
    public ResponseEntity<? super PostNoticeResponseDto> postNotice(PostNoticeRequestDto dto){
        try{
            NoticeEntity noticeEntity = new NoticeEntity(dto);
            noticeRepository.save(noticeEntity);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostNoticeResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchNoticeResponseDto> patchNotice(PatchNoticeRequestDto dto, Long NoticeId){
    try{
        NoticeEntity noticeEntity = noticeRepository.findByNoticeId(NoticeId);
        if(noticeEntity == null) return PatchNoticeResponseDto.notExistNotice();
        noticeEntity.patchNotice(dto);
        noticeRepository.save(noticeEntity);
    } catch(Exception exception){
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }
    return PatchNoticeResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteNoticeResponseDto> deleteNotice(Long NoticeId){
        try{
            NoticeEntity noticeEntity = noticeRepository.findByNoticeId(NoticeId);
            if(noticeEntity==null) return DeleteNoticeResponseDto.notExistNotice();
            noticeRepository.delete(noticeEntity);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteNoticeResponseDto.success();
    }
    @Override
    public ResponseEntity<? super GetAllNoticeResponseDto> getAllNotice(){
        List<NoticeEntity> notices= null;
        try{
            notices = noticeRepository.findAll();
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAllNoticeResponseDto.success(notices);
    }
}
