package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.rate.PostRateRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.rate.GeAverageRateResponseDto;
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
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<? super GeAverageRateResponseDto> getRateAverage(List<String> contentIds) {
        Map<String, Double> averages = contentIds.stream()
                .collect(Collectors.toMap(
                        contentId -> contentId,
                        contentId -> {
                            List<RateEntity> rates = rateRepository.findByContentId(contentId);
                            return rates.stream()
                                    .mapToInt(RateEntity::getRate)
                                    .average()
                                    .orElse(0.0);
                        }
                ));

        return GeAverageRateResponseDto.success(averages);
    }
}
