package com.fitness.activityservice.controller;


import com.fitness.activityservice.dto.ActivityRequestDto;
import com.fitness.activityservice.dto.ActivityResponseDto;
import com.fitness.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {


    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDto> trackActivity(@RequestBody ActivityRequestDto request){
        return ResponseEntity.ok(activityService.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDto>> getUserActivities(@RequestHeader("X-User-ID")String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }
}
