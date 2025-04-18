package com.rms.extranet.service;

import com.rms.extranet.model.MemberContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MemberContentService {

    private static final String CONTENT_URL = "https://www.connect2nse.com/extranet-api/member/content/2.0";

    @Autowired
    private RestTemplate restTemplate;

    public MemberContentResponse getMemberFilesAndFolders(String authorizationToken, String segment, String folderPath) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        StringBuilder urlBuilder = new StringBuilder(CONTENT_URL);
        urlBuilder.append("?segment=").append(segment);

        if (folderPath != null && !folderPath.isEmpty()) {
            urlBuilder.append("&folderPath=").append(folderPath);
        }

        ResponseEntity<MemberContentResponse> response = restTemplate.exchange(
                urlBuilder.toString(),
                HttpMethod.GET,
                httpEntity,
                MemberContentResponse.class
        );

        return response.getBody();
    }
}
