package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.meeting.PostMeetingResponseDto;
import com.korea.WhereToGo.entity.ImageEntity;
import com.korea.WhereToGo.entity.MeetingEntity;
import com.korea.WhereToGo.repository.ImageRepository;
import com.korea.WhereToGo.repository.MeetingRepository;
import com.korea.WhereToGo.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingImplement implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super PostMeetingResponseDto> postMeeting(PostMeetingRequestDto dto) {
        try {
            MeetingEntity meetingEntity = new MeetingEntity(dto);
            meetingRepository.save(meetingEntity);

            String meetingImageUrl = dto.getImageUrl();
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setImage(meetingImageUrl);
            imageEntity.setMeeting(meetingEntity);
            imageRepository.save(imageEntity);

            meetingEntity.setImage(imageEntity);
            meetingRepository.save(meetingEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostMeetingResponseDto.success();
    }
}
