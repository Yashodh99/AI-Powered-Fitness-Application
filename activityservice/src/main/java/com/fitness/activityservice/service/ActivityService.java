package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequestDto;
import com.fitness.activityservice.dto.ActivityResponseDto;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final  ActivityRepository activityRepository;

    public ActivityResponseDto trackActivity(ActivityRequestDto request) {

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);

    }

    private ActivityResponseDto mapToResponse(Activity activity){
        ActivityResponseDto responseDto = new ActivityResponseDto();
        responseDto.setId(activity.getId());
        responseDto.setUserId(activity.getUserId());
        responseDto.setDuration(activity.getDuration());
        responseDto.setType(activity.getType());
        responseDto.setCaloriesBurned(activity.getCaloriesBurned());
        responseDto.setStartTime(activity.getStartTime());
        responseDto.setAdditionalMetrics(activity.getAdditionalMetrics());
        responseDto.setCreatedAt(activity.getCreatedAt());
        responseDto.setUpdatedAt(activity.getUpdatedAt());

        return responseDto;

    }
}
