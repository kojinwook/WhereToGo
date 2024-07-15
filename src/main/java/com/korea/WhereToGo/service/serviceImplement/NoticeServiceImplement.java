package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.notice.PatchNoticeRequestDto;
import com.korea.WhereToGo.dto.request.notice.PostNoticeRequestDto;
import com.korea.WhereToGo.dto.response.notice.*;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.NoticeEntity;
import com.korea.WhereToGo.repository.NoticeRepository;
import com.korea.WhereToGo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Override
    public ResponseEntity<? super GetSearchNoticeListResponseDto> searchNoticeList(String keyword){
        List<NoticeEntity> noticeEntities = new ArrayList<>();
        try{
            noticeEntities = noticeRepository.findByTitleContainingOrContentContaining(keyword, keyword);
            if(noticeEntities.isEmpty()) return GetSearchNoticeListResponseDto.notExistNotice();
        } catch(Exception exception){
            return ResponseDto.databaseError();
        }
        return GetSearchNoticeListResponseDto.success(noticeEntities);
    }
}
