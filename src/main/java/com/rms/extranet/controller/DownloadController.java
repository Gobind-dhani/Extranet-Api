package com.rms.extranet.controller;



import com.rms.extranet.model.DownloadFileRequest;
import com.rms.extranet.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/download")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("/file")
    public ResponseEntity<Resource> downloadFile(@RequestHeader("Authorization") String authorizationToken,
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

            Resource fileResource = downloadService.downloadFile(authorizationToken, downloadRequest);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + (filename != null ? filename : "downloadedFile") + "\"")
                    .body(fileResource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
