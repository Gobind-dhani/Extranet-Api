package com.rms.extranet.service;



import com.rms.extranet.model.DownloadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DownloadService {

    private static final String DOWNLOAD_URL = "https://www.connect2nse.com/extranet-api/member/file/download/1.0";

    @Autowired
    private RestTemplate restTemplate;

    public Resource downloadFile(String authorizationToken, DownloadFileRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationToken);

        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        StringBuilder urlBuilder = new StringBuilder(DOWNLOAD_URL);
        urlBuilder.append("?segment=").append(request.getSegment());
        urlBuilder.append("&folderPath=").append(request.getFolderPath());

        if (request.getDate() != null && !request.getDate().isEmpty()) {
            urlBuilder.append("&date=").append(request.getDate());
        }
        if (request.getFilename() != null && !request.getFilename().isEmpty()) {
            urlBuilder.append("&filename=").append(request.getFilename());
        }

        ResponseEntity<Resource> response = restTemplate.exchange(
                urlBuilder.toString(),
                HttpMethod.GET,
                httpEntity,
                Resource.class
        );

        return response.getBody();
    }
}

