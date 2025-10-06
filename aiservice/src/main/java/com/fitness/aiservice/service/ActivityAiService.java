package com.fitness.aiservice.service;


import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAiService {

    private final GeminiService geminiService;

    public String generateRecommendation(Activity activity){
        String prompt = createPromptForActivity(activity);
        String aiResponse = geminiService.getAnswer(prompt);
        log.info("RESPONSE FROM AI: {}", aiResponse);
        return aiResponse;
    }

    private String createPromptForActivity(Activity activity) {
        return String.format("""
        Analyze this fitness activity and provide detailed recommendations in JSON format.

        {
            "activity": {
                "type": "%s",
                "duration": "%d minutes",
                "caloriesBurned": "%d",
                "startTime": "%s"
            },
            "analysis": {
                "overall": "Provide an overall summary of the user's performance.",
                "pace": "Evaluate the user's pace and how consistent it was.",
                "heartRate": "Analyze the user's heart rate trends and intensity level.",
                "caloriesBurned": "Comment on the calories burned and efficiency of the workout."
            },
            "improvements": [
                {
                    "area": "Pace consistency",
                    "recommendation": "Suggest ways to maintain a steady pace throughout the workout."
                },
                {
                    "area": "Heart rate control",
                    "recommendation": "Provide tips on improving cardiovascular endurance and recovery."
                },
                {
                    "area": "Workout efficiency",
                    "recommendation": "Recommend exercises or adjustments to burn calories more effectively."
                }
            ]
        }
        """,
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getStartTime()
        );
    }


}
