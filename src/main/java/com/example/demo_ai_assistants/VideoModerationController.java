package com.example.demo_ai_assistants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/video-moderation")
public class VideoModerationController {

    @Autowired
    private VideoModerationService videoModerationService;

    /**
     * API chính - trả về JSON với format tùy chỉnh theo yêu cầu
     */
    @PostMapping("/moderate-custom")
    public ResponseEntity<String> moderateVideoCustomFormat(@RequestBody Map<String, String> request) {
        String videoUrl = request.get("videoUrl");

        if (videoUrl == null || videoUrl.trim().isEmpty()) {
            String errorResponse = "{\"response_fromai\": \"Approved: False\\nDescription: Video URL is required\", \"content\": \"URL video không được để trống\"}";
            return ResponseEntity.badRequest().body(errorResponse);
        }

        String result = videoModerationService.moderateVideoCustomFormat(videoUrl);
        return ResponseEntity.ok(result);
    }

    /**
     * API đơn giản cho testing
     */
    @PostMapping("/moderate")
    public ResponseEntity<String> moderateVideoSimple(@RequestBody Map<String, String> request) {
        return moderateVideoCustomFormat(request);
    }
}