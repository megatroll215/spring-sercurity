package com.tuan.springsercurity.util;

import com.google.gson.Gson;
import com.tuan.springsercurity.dtos.response.CommonResponse;
import com.tuan.springsercurity.exception.InternalException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@Slf4j
public class RestUtil {

    @Autowired
    private RestTemplate restTemplate;

    public String get(String url) {

        return restTemplate.getForObject(url, String.class);
    }

    @SneakyThrows
    public String getForSmsBrandName(String url, String sid, String sender, String recipient, String content) {

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("sid", sid)
                .queryParam("sender", sender)
                .queryParam("recipient", recipient)
                .queryParam("content", content)
                .build()
                .toUri();
        String strUri = uri.toString();
        strUri = strUri.replace("+","%2B");

        return restTemplate.getForObject(new URI(strUri), String.class);
    }

    public <T> String post(String url, T body,String apiKey) {
        log.info("====begin POST====");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (apiKey != null) {
            headers.set("apiKey",apiKey);

        }
        HttpEntity<String> requestEntity = new HttpEntity<>(new Gson().toJson(body), headers);
        // Make the POST request
        try {
            String response = restTemplate.postForObject(url, requestEntity, String.class);

            log.info("POST Response: {}", response);
            return response;
        }
        catch (Exception e){
            throw new InternalException(e.getMessage());
        }
    }

    public String  postWithParam(String url, MultiValueMap<String,String> queryParams) {
        log.info("====begin POST Logout====");

        // Tạo một HttpHeaders để chứa các headers nếu cần
        HttpHeaders headers = new HttpHeaders();

        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("logout_token", queryParams.get("logout_token"))
                .build()
                .toUri();
        // Tạo một HttpEntity chỉ với queryParams
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // Gửi HTTP POST request và nhận lại một String response
        String response = restTemplate.postForObject(uri, requestEntity, String.class);

        return response;
    }

    public CommonResponse<Object> toCustomResponse(String response) throws Exception {

        Gson gson = new Gson();

        return gson.fromJson(response, CommonResponse.class);
    }

}
