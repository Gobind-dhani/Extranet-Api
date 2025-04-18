package com.rms.extranet.service;

import com.rms.extranet.model.DownloadFileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@Service
public class DownloadService {

    private static final String DOWNLOAD_URL = "https://www.connect2nse.com/extranet-api/member/file/download/2.0";

    @Autowired
    private RestTemplate restTemplate;

    public String downloadAndSaveFile(String authorizationToken, DownloadFileRequest request) {
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

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            try {
                // Your Downloads folder (for Windows) or modify accordingly for Linux/Mac
                String downloadsPath = System.getProperty("user.home") + "/Downloads";
                Path downloadDir = Paths.get(downloadsPath);

                // Create folder if not exists
                if (!Files.exists(downloadDir)) {
                    Files.createDirectories(downloadDir);
                }

                // Define full file path
                String filename = request.getFilename() != null ? request.getFilename() : "downloaded_file";
                Path filePath = downloadDir.resolve(filename);

                // Save the file
                try (InputStream inputStream = response.getBody().getInputStream()) {
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                return "File downloaded successfully to: " + filePath.toAbsolutePath();
            } catch (IOException e) {
                return "Failed to save file: " + e.getMessage();
            }
        } else {
            return "Failed to download file: HTTP Status " + response.getStatusCode();
        }
    }
}
