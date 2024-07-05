package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.rate.PostRateRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.rate.GetRateAverageResponseDto;
import com.korea.WhereToGo.dto.response.rate.PostRateResponseDto;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.entity.RateEntity;
import com.korea.WhereToGo.repository.FestivalRepository;
import com.korea.WhereToGo.repository.RateRepository;
import com.korea.WhereToGo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class RateServiceImplement implements RateService {

    private final FestivalRepository festivalRepository;
    private final RateRepository rateRepository;
    @Override
    public ResponseEntity<? super PostRateResponseDto> postRate(PostRateRequestDto dto) {
        String contentId = dto.getContentId();
        try {
            FestivalEntity festivalEntity = festivalRepository.findByContentId(contentId);
            if (festivalEntity == null) return PostRateResponseDto.notExistFestival();

            RateEntity rateEntity = new RateEntity(dto, contentId);
            rateRepository.save(rateEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostRateResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetRateAverageResponseDto> getRateAverage(String contentId) {
        List<RateEntity> rateEntities = rateRepository.findAllByContentId(contentId);
        OptionalDouble average = null;
        try{

            if(rateEntities.isEmpty()) return GetRateAverageResponseDto.notExistFestival();

            average = rateEntities.stream()
                    .mapToInt(RateEntity::getRate)
                    .average();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetRateAverageResponseDto.success(average);
    }
}
