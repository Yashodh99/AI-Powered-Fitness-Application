//package com.fitness.aiservice.service;
//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fitness.aiservice.model.Activity;
//import com.fitness.aiservice.model.Recommendation;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class ActivityAiService {
//
//    private final GeminiService geminiService;
//
//    public Recommendation generateRecommendation(Activity activity){
//        String prompt = createPromptForActivity(activity);
//        String aiResponse = geminiService.getAnswer(prompt);
//        log.info("RESPONSE FROM AI: {}", aiResponse);
//
//        return  processAiResponse(activity,aiResponse);
//    }
//
//    private Recommendation processAiResponse(Activity activity, String aiResponse){
//        try{
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode rootNode = mapper.readTree(aiResponse);
//
//            JsonNode textNode = rootNode.path("candidates")
//                    .get(0)
//                    .path("content")
//                    .path("parts")
//                    .get(0)
//                    .path("text");
//
//            String jsonContent = textNode.asText()
//                    .replaceAll("''' json\\n", "")
//                    .replaceAll("\\n'''", "")
//                    .trim();
//
////            log.info("PARSED RESPONSE FROM AI: {}", jsonContent);
//
//            JsonNode analysisJson = mapper.readTree(jsonContent);
//            JsonNode analysisNode = analysisJson.path("analysis");
//            StringBuilder fullAnalysis = new StringBuilder();
//            addAnalysisSection(fullAnalysis, analysisNode, "overall", "Overall:");
//            addAnalysisSection(fullAnalysis, analysisNode, "pace", "Pace:");
//            addAnalysisSection(fullAnalysis, analysisNode, "heartRate", "Heart Rate:");
//            addAnalysisSection(fullAnalysis, analysisNode, "caloriesBurned", "CaloriesBurned:");
//
//
//            List<String> improvements = extractImprovements(analysisJson.path("improvements"));
//            List<String> suggestions = extractSuggestions(analysisJson.path("suggestions"));
//            List<String> safety = extractSafetyGuideLines(analysisJson.path("safety"));
//
//
//            return Recommendation.builder()
//                    .activityId(activity.getId())
//                    .userId(activity.getUserId())
//                    .activity(activity.getType())
//                    .recommendation(fullAnalysis.toString().trim())
//                    .improvements(improvements)
//                    .suggestions(suggestions)
//                    .safety(safety)
//                    .createdAt(LocalDateTime.now())
//                    .build();
//
//        }catch(Exception e){
//            e.printStackTrace();
//            return  createDefaultRecommendation(activity);
//        }
//    }
//
//    private Recommendation createDefaultRecommendation(Activity activity) {
//        return Recommendation.builder()
//                .activityId(activity.getId())
//                .userId(activity.getUserId())
//                .activity(activity.getType())
//                .recommendation("Unable to generate detailed analysis")
//                .improvements(Collections.singletonList("Continue with your current routine"))
//                .suggestions(Collections.singletonList("Consider consulting a fitness expert for personalized guidance"))
//                .safety(Arrays.asList(
//                        "Always warm up before exercise",
//                        "Stay hydrated",
//                        "Listen to your body"
//                ))
//                .createdAt(LocalDateTime.now())
//                .build();
//    }
//
//
//    private List<String> extractSafetyGuideLines(JsonNode safetyNode) {
//        List<String> safety = new ArrayList<>();
//        if(safetyNode.isArray()){
//            safetyNode.forEach(item -> safety.add(item.asText()));
//
//        }
//        return safety.isEmpty()?
//                Collections.singletonList("Follow safety guidelines"):
//                safety;
//    }
//
//    private List<String> extractSuggestions(JsonNode suggestionNode) {
//        List<String> suggestions = new ArrayList<>();
//        if (suggestionNode.isArray()) {
//            suggestionNode.forEach(suggestion -> {
//                String workout = suggestion.path("workout").asText();
//                String description = suggestion.path("description").asText();
//                suggestions.add(String.format("$s: %s", workout, description));
//            });
//
//        }
//        return suggestions.isEmpty() ?
//                Collections.singletonList("No specific suggestions provided") :
//                suggestions;
//
//    }
//
//    private List<String> extractImprovements(JsonNode improvementsNode) {
//        List<String> improvements = new ArrayList<>();
//        if(improvementsNode.isArray()){
//            improvementsNode.forEach(improvement ->{
//                String area = improvement.path("area").asText();
//                String detail = improvement.path("recommendation").asText();
//                improvements.add(String.format("%s: %s", area, detail));
//
//            });
//
//        }
//        return improvements.isEmpty()?
//                Collections.singletonList("No specific improvements provided") :
//                improvements;
//    }
//
//    private void addAnalysisSection(StringBuilder fullAnalysis, JsonNode analysisNode, String key, String prefix) {
//        if(!analysisNode.path(key).isMissingNode()){
//            fullAnalysis.append(prefix)
//                    .append(analysisNode.path(key).asText())
//                    .append("\n\n");
//        }
//    }
//
//    private String createPromptForActivity(Activity activity) {
//        return String.format("""
//        Analyze this fitness activity and provide detailed recommendations in JSON format.
//
//        {
//            "activity": {
//                "type": "%s",
//                "duration": "%d minutes",
//                "caloriesBurned": "%d",
//                "startTime": "%s"
//            },
//            "analysis": {
//                "overall": "Provide an overall summary of the user's performance.",
//                "pace": "Evaluate the user's pace and how consistent it was.",
//                "heartRate": "Analyze the user's heart rate trends and intensity level.",
//                "caloriesBurned": "Comment on the calories burned and efficiency of the workout."
//            },
//            "improvements": [
//                {
//                    "area": "Pace consistency",
//                    "recommendation": "Suggest ways to maintain a steady pace throughout the workout."
//                },
//                {
//                    "area": "Heart rate control",
//                    "recommendation": "Provide tips on improving cardiovascular endurance and recovery."
//                },
//                {
//                    "area": "Workout efficiency",
//                    "recommendation": "Recommend exercises or adjustments to burn calories more effectively."
//                }
//            ]
//        }
//        """,
//                activity.getType(),
//                activity.getDuration(),
//                activity.getCaloriesBurned(),
//                activity.getStartTime()
//        );
//    }
//
//
//}


package com.fitness.aiservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityAiService {

    private final GeminiService geminiService;

    public Recommendation generateRecommendation(Activity activity) {
        String prompt = createPromptForActivity(activity);
        String aiResponse = geminiService.getAnswer(prompt);
        log.info("RESPONSE FROM AI: {}", aiResponse);
        return processAiResponse(activity, aiResponse);
    }

    private Recommendation processAiResponse(Activity activity, String aiResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // 1) Parse the model envelope
            JsonNode rootNode = mapper.readTree(aiResponse);
            JsonNode textNode = rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            String text = textNode.asText();
            String jsonContent = extractJsonBlock(text);

            // 2) Parse the actual JSON that the model produced
            JsonNode analysisJson = mapper.readTree(jsonContent);
            JsonNode analysisNode = analysisJson.path("analysis");

            StringBuilder fullAnalysis = new StringBuilder();
            addAnalysisSection(fullAnalysis, analysisNode, "overall", "Overall:");
            addAnalysisSection(fullAnalysis, analysisNode, "pace", "Pace:");
            addAnalysisSection(fullAnalysis, analysisNode, "heartRate", "Heart Rate:");
            addAnalysisSection(fullAnalysis, analysisNode, "caloriesBurned", "Calories burned:");

            List<String> improvements = extractImprovements(analysisJson.path("improvements"));
            List<String> suggestions = extractSuggestions(analysisJson.path("suggestions"));
            List<String> safety = extractSafetyGuideLines(analysisJson.path("safety"));

            return Recommendation.builder()
                    .activityId(activity.getId())
                    .userId(activity.getUserId())
                    .activity(activity.getType())
                    .recommendation(fullAnalysis.toString().trim())
                    .improvements(improvements)
                    .suggestions(suggestions)
                    .safety(safety)
                    .createdAt(LocalDateTime.now())
                    .build();

        } catch (Exception e) {
            log.error("Failed to process AI response", e);
            return createDefaultRecommendation(activity);
        }
    }


    private String extractJsonBlock(String text) {
        String t = text == null ? "" : text.trim();


        if (t.startsWith("{") || t.startsWith("[")) {
            return t;
        }


        Pattern fenced = Pattern.compile("```(?:json)?\\s*(.*?)\\s*```", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher m = fenced.matcher(t);
        if (m.find()) {
            return m.group(1).trim();
        }


        Pattern tripleQuotes = Pattern.compile("'''(?:json)?\\s*(.*?)\\s*'''", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher m2 = tripleQuotes.matcher(t);
        if (m2.find()) {
            return m2.group(1).trim();
        }

        return t;
    }

    private Recommendation createDefaultRecommendation(Activity activity) {
        return Recommendation.builder()
                .activityId(activity.getId())
                .userId(activity.getUserId())
                .activity(activity.getType())
                .recommendation("Unable to generate detailed analysis")
                .improvements(Collections.singletonList("Continue with your current routine"))
                .suggestions(Collections.singletonList("Consider consulting a fitness expert for personalized guidance"))
                .safety(Arrays.asList(
                        "Always warm up before exercise",
                        "Stay hydrated",
                        "Listen to your body"
                ))
                .createdAt(LocalDateTime.now())
                .build();
    }

    private List<String> extractSafetyGuideLines(JsonNode safetyNode) {
        List<String> safety = new ArrayList<>();
        if (safetyNode != null && safetyNode.isArray()) {
            safetyNode.forEach(item -> safety.add(item.asText()));
        }
        return safety.isEmpty()
                ? Collections.singletonList("Follow safety guidelines")
                : safety;
    }

    private List<String> extractSuggestions(JsonNode suggestionNode) {
        List<String> suggestions = new ArrayList<>();
        if (suggestionNode != null && suggestionNode.isArray()) {
            suggestionNode.forEach(s -> {
                String workout = s.path("workout").asText();
                String description = s.path("description").asText();
                suggestions.add(String.format("%s: %s", workout, description)); // <-- fixed %s
            });
        }
        return suggestions.isEmpty()
                ? Collections.singletonList("No specific suggestions provided")
                : suggestions;
    }

    private List<String> extractImprovements(JsonNode improvementsNode) {
        List<String> improvements = new ArrayList<>();
        if (improvementsNode != null && improvementsNode.isArray()) {
            improvementsNode.forEach(improvement -> {
                String area = improvement.path("area").asText();
                String detail = improvement.path("recommendation").asText();
                improvements.add(String.format("%s: %s", area, detail));
            });
        }
        return improvements.isEmpty()
                ? Collections.singletonList("No specific improvements provided")
                : improvements;
    }

    private void addAnalysisSection(StringBuilder fullAnalysis, JsonNode analysisNode, String key, String prefix) {
        if (analysisNode != null && analysisNode.hasNonNull(key) && analysisNode.path(key).isTextual()) {
            fullAnalysis.append(prefix)
                    .append(' ')
                    .append(analysisNode.path(key).asText())
                    .append("\n\n");
        }
    }

    private String createPromptForActivity(Activity activity) {
        return String.format("""
                Analyze this fitness activity and return ONLY valid JSON (no code fences, no markdown, no explanations).

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
                    {"area": "Pace consistency","recommendation": "Suggest ways to maintain a steady pace."},
                    {"area": "Heart rate control","recommendation": "Tips to improve cardiovascular endurance."},
                    {"area": "Workout efficiency","recommendation": "Ways to burn calories more effectively."}
                  ],
                  "suggestions": [
                    {"workout": "Optional workout name","description": "Short description"}
                  ],
                  "safety": ["Optional safety tips"]
                }
                """,
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getStartTime()
        );
    }
}
