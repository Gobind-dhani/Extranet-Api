package com.rms.extranet.controller;

import com.rms.extranet.model.MemberContentResponse;
import com.rms.extranet.service.MemberContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberContentController {

    @Autowired
    private MemberContentService memberContentService;

    @GetMapping("/files")
    public MemberContentResponse getMemberFilesAndFolders(
            @RequestHeader("Authorization") String authorizationToken,
            @RequestParam String segment,
            @RequestParam(required = false) String folderPath) {
        return memberContentService.getMemberFilesAndFolders(authorizationToken, segment, folderPath);
    }
}
