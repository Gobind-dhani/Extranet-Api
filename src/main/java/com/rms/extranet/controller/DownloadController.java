package com.rms.extranet.controller;

import com.rms.extranet.model.DownloadFileRequest;
import com.rms.extranet.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("/file")
    public ResponseEntity<String> downloadFile(@RequestHeader("Authorization") String authorizationToken,
                                               @RequestParam String segment,
                                               @RequestParam String folderPath,
                                               @RequestParam(required = false) String date,
                                               @RequestParam(required = false) String filename) {
        try {
            DownloadFileRequest downloadRequest = new DownloadFileRequest();
            downloadRequest.setSegment(segment);
            downloadRequest.setFolderPath(folderPath);
            downloadRequest.setDate(date);
            downloadRequest.setFilename(filename);

            String resultMessage = downloadService.downloadAndSaveFile(authorizationToken, downloadRequest);

            return ResponseEntity.ok(resultMessage);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error occurred during file download: " + e.getMessage());
        }
    }
}
