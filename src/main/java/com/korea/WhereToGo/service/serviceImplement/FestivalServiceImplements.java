package com.korea.WhereToGo.service.serviceImplement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.festival.PostFestivalListResponseDto;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.repository.FestivalRepository;
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

@Service
@RequiredArgsConstructor
public class FestivalServiceImplements implements FestivalService {

    private static final String API_URL = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
    private static final String SERVICE_KEY = "jyrjzPCPy2ZunbDHSvrxNcr1Jl%2BWUNSidHGaWa0ZtEPPpAeF%2FCXZlJu9%2FInRdrmT7z29NspgBpW3ebiR3qBQ%2FQ%3D%3D";
    private final FestivalRepository festivalRepository;

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
                    festivalEntity.setModifyDate(itemNode.path("modifiedtime").asText());
                    festivalEntity.setAreaCode(itemNode.path("areacode").asText());
                    festivalEntity.setSigunguCode(itemNode.path("sigungucode").asText());
                    festivalEntity.setContentId(itemNode.path("contentid").asText());
                    festivalEntity.setContentTypeId(itemNode.path("contenttypeid").asText());

                    FestivalEntity festival = festivalRepository.findByContentId(festivalEntity.getContentId());
                    if(festival != null) return PostFestivalListResponseDto.duplicate();

                    festivalRepository.save(festivalEntity);
                }
            }
        } catch (Exception e) {
            return ResponseDto.databaseError();
        }
        return PostFestivalListResponseDto.success();
    }
}
