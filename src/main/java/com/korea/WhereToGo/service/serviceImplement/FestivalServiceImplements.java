package com.korea.WhereToGo.service.serviceImplement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korea.WhereToGo.dto.request.festival.PatchFestivalRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.festival.*;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.repository.FestivalRepository;
import com.korea.WhereToGo.repository.ReviewRepository;
import com.korea.WhereToGo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalServiceImplements implements FestivalService {

    private static final String API_URL = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
    private static final String SERVICE_KEY = "jyrjzPCPy2ZunbDHSvrxNcr1Jl%2BWUNSidHGaWa0ZtEPPpAeF%2FCXZlJu9%2FInRdrmT7z29NspgBpW3ebiR3qBQ%2FQ%3D%3D";
    private final FestivalRepository festivalRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<? super PostFestivalListResponseDto> saveFestivalList(String eventStartDate) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response;

        try {
            String encodedStartDate = URLEncoder.encode(eventStartDate, StandardCharsets.UTF_8.toString());
            String url = String.format("%s?MobileOS=WIN&MobileApp=A&_type=json&eventStartDate=%s&serviceKey=%s&numOfRows=1000",
                    API_URL, encodedStartDate, SERVICE_KEY);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println(response);
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String todayFormatted = today.format(formatter);

            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    FestivalEntity festivalEntity = new FestivalEntity();
                    festivalEntity.setTitle(itemNode.path("title").asText());
                    festivalEntity.setStartDate(itemNode.path("eventstartdate").asText());
                    festivalEntity.setEndDate(itemNode.path("eventenddate").asText());
                    festivalEntity.setAddress1(itemNode.path("addr1").asText());
                    festivalEntity.setFirstImage(itemNode.path("firstimage").asText(null));
                    festivalEntity.setTel(itemNode.path("tel").asText(null));
                    festivalEntity.setMapX(itemNode.path("mapx").asText());
                    festivalEntity.setMapY(itemNode.path("mapy").asText());
                    String modifiedTimeStr = itemNode.path("modifiedtime").asText().substring(0, 8);
                    LocalDate modifyDate = LocalDate.parse(modifiedTimeStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    festivalEntity.setModifyDate(modifyDate.toString());
                    festivalEntity.setAreaCode(itemNode.path("areacode").asText());
                    festivalEntity.setSigunguCode(itemNode.path("sigungucode").asText());
                    festivalEntity.setContentId(itemNode.path("contentid").asText());
                    festivalEntity.setContentTypeId(itemNode.path("contenttypeid").asText());

//                    if (!modifyDate.isEqual(LocalDate.parse(todayFormatted, DateTimeFormatter.ofPattern("yyyyMMdd")))) {
//                        continue;
//                    }
//
//                    FestivalEntity existingFestival = festivalRepository.findByContentId(festivalEntity.getContentId());
//
//                    if (existingFestival != null) {
//                        boolean isUpdated = false;
//                        if (!existingFestival.getTitle().equals(festivalEntity.getTitle())) {
//                            existingFestival.setTitle(festivalEntity.getTitle());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getStartDate().equals(festivalEntity.getStartDate())) {
//                            existingFestival.setStartDate(festivalEntity.getStartDate());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getEndDate().equals(festivalEntity.getEndDate())) {
//                            existingFestival.setEndDate(festivalEntity.getEndDate());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getAddress1().equals(festivalEntity.getAddress1())) {
//                            existingFestival.setAddress1(festivalEntity.getAddress1());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getFirstImage().equals(festivalEntity.getFirstImage())) {
//                            existingFestival.setFirstImage(festivalEntity.getFirstImage());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getTel().equals(festivalEntity.getTel())) {
//                            existingFestival.setTel(festivalEntity.getTel());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getMapX().equals(festivalEntity.getMapX())) {
//                            existingFestival.setMapX(festivalEntity.getMapX());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getMapY().equals(festivalEntity.getMapY())) {
//                            existingFestival.setMapY(festivalEntity.getMapY());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getModifyDate().equals(festivalEntity.getModifyDate())) {
//                            existingFestival.setModifyDate(festivalEntity.getModifyDate());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getAreaCode().equals(festivalEntity.getAreaCode())) {
//                            existingFestival.setAreaCode(festivalEntity.getAreaCode());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getSigunguCode().equals(festivalEntity.getSigunguCode())) {
//                            existingFestival.setSigunguCode(festivalEntity.getSigunguCode());
//                            isUpdated = true;
//                        }
//                        if (!existingFestival.getContentTypeId().equals(festivalEntity.getContentTypeId())) {
//                            existingFestival.setContentTypeId(festivalEntity.getContentTypeId());
//                            isUpdated = true;
//                        }
//
//                        if (isUpdated) {
//                            festivalRepository.save(existingFestival);
//                        }
//                    } else {
                        festivalRepository.save(festivalEntity);
//                    }
                }
            }
        } catch (Exception e) {
            return ResponseDto.databaseError();
        }
        return PostFestivalListResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetFestivalListResponseDto> getFestivalList() {

        List<FestivalEntity> festivalEntities = new ArrayList<>();

        try {
            festivalEntities = festivalRepository.findAll();

        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }
        return GetFestivalListResponseDto.success(festivalEntities);
    }

    @Override
    public ResponseEntity<? super GetSearchFestivalListResponseDto> searchFestivalList(String areaCode) {
        List<FestivalEntity> festivalEntities = new ArrayList<>();
        try {
            festivalEntities = festivalRepository.findByAreaCode(areaCode);

            if (festivalEntities.isEmpty()) return GetSearchFestivalListResponseDto.notExistFestival();

        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }
        return GetSearchFestivalListResponseDto.success(festivalEntities);
    }

    @Override
    public ResponseEntity<? super PatchFestivalResponseDto> patchFestival(PatchFestivalRequestDto dto, String contentId, String userId) {
        try {
            FestivalEntity festivalEntity = festivalRepository.findByContentId(contentId);
            if (festivalEntity == null) return PatchFestivalResponseDto.notExistFestival();

//            UserEntity userEntity = userRepository.findByUserId(userId);
//            if (userEntity == null || !userEntity.getRole().equals("ADMIN")) return PatchFestivalResponseDto.notPermission();

            festivalEntity.patchFestival(dto);
            festivalRepository.save(festivalEntity);

        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }
        return PatchFestivalResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetFestivalResponseDto> getFestival(String contentId) {
        FestivalEntity festivalEntity = new FestivalEntity();

        try {

            festivalEntity = festivalRepository.findByContentId(contentId);
            if (festivalEntity == null) return GetFestivalResponseDto.notExistFestival();

        } catch (Exception exception) {
            return ResponseDto.databaseError();
        }
        return GetFestivalResponseDto.success(festivalEntity);
    }
}
