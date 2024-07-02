package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.festival.PostFestivalListResponseDto;
import com.korea.WhereToGo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class FestivalServiceImplements implements FestivalService {

    private static final String API_URL = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
    private static final String SERVICE_KEY = "jyrjzPCPy2ZunbDHSvrxNcr1Jl%2BWUNSidHGaWa0ZtEPPpAeF%2FCXZlJu9%2FInRdrmT7z29NspgBpW3ebiR3qBQ%2FQ%3D%3D";
    @Override
    public ResponseEntity<? super PostFestivalListResponseDto> saveFestivalList(String eventStartDate) {
        RestTemplate restTemplate = new RestTemplate();
        URLEncoder.encode(eventStartDate);
        ResponseEntity<String> response = null;
        try {
            String url = String.format("%s?MobileOS=WIN&MobileApp=A&_type=json&eventStartDate=%s&serviceKey=%s",
                    API_URL, eventStartDate, SERVICE_KEY);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);

            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println(response.getBody());

        } catch (Exception e) {
            return ResponseDto.databaseError();
        }
        return PostFestivalListResponseDto.success(response.getBody());
    }
}
