package com.backend.ITS03.controller;

import com.backend.ITS03.entity.InterviewSchedule;
import com.backend.ITS03.entity.UserProfile;
import com.backend.ITS03.service.InterviewScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/interviewSchedule")
public class InterviewScheduleController {

    @Autowired
    private InterviewScheduleService interviewScheduleService;

    @GetMapping("/allInterviews")
    public ResponseEntity<List<InterviewSchedule>> getAllInterviews()
    {
        List<InterviewSchedule> interviewSchedules = interviewScheduleService.findAllInterviews();
        return new ResponseEntity<>(interviewSchedules, HttpStatus.OK);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserProfile> getUserByUserId(@PathVariable("userId") String userId)
    {
        UserProfile userProfile = interviewScheduleService.fetchCandidateData(userId);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/findRating/{userId}")
    public ResponseEntity<Map<String , String>> getRating(@PathVariable("userId") String userId)
    {
        InterviewSchedule rating = interviewScheduleService.findUserRating(userId);
        return new ResponseEntity<>(getCandidateInterviewRating(rating), HttpStatus.OK);
    }

    @PostMapping("/addInterview")
    public ResponseEntity<Map<String, String>> addInterview(@RequestBody Map<String, Object> userMap) {
        String userId = (String) userMap.get("userId");
        String interviewType = (String) userMap.get("interviewType");
        String interviewDate = (String) userMap.get("interviewDate");
        String candidateId = (String) userMap.get("candidateId");
        String rating = (String) userMap.get("rating");

        InterviewSchedule interviewSchedule = interviewScheduleService.addInterview(userId, candidateId, interviewType, interviewDate, rating);
        return new ResponseEntity<>(generateInterviewResponse(interviewSchedule), HttpStatus.OK);
    }

    @PutMapping("/updateInterview")
    public ResponseEntity<InterviewSchedule> updateInterview(@RequestBody InterviewSchedule interviewSchedule)
    {
        InterviewSchedule updateInterview = interviewScheduleService.updateInterview(interviewSchedule);
        return new ResponseEntity<>(updateInterview, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteInterview(@PathVariable("id") Long id)
    {
        interviewScheduleService.deleteInterview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Map<String, String> generateInterviewResponse(InterviewSchedule interviewSchedule) {

        Map<String, String> map = new HashMap<>();
        map.put("message","Interview Scheduled");
        map.put("userId",interviewSchedule.getUserId());
        map.put("interviewId",interviewSchedule.getInterviewId());
        map.put("candidateId",interviewSchedule.getCandidateId());
        return map;
    }

    private Map<String, String> getCandidateInterviewRating(InterviewSchedule rating)
    {
        Map<String, String> map = new HashMap<>();
        map.put("message","Rating of Interview");
        map.put("rating",rating.getRating());
        return map;
    }
}
