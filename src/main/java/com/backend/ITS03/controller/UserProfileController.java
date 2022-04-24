package com.backend.ITS03.controller;

import com.backend.ITS03.entity.InterviewSchedule;
import com.backend.ITS03.entity.UserProfile;
import com.backend.ITS03.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/userProfile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;


    @GetMapping("/allUserProfiles")
    public ResponseEntity<List<UserProfile>> getAllUsers()
    {
        List<UserProfile> userProfiles = userProfileService.findAllUserProfiles();
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    @GetMapping("/find/{designation}")
    public ResponseEntity<List<UserProfile>> getUsersByQualification(@PathVariable("designation") String designation)
    {
        List<UserProfile> userProfiles = userProfileService.findUserByDesignation(designation);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    @GetMapping("/find/{primarySkillSet}")
    public ResponseEntity<List<UserProfile>> getUserByPrimary(@PathVariable("primarySkillSet") String primarySkillSet)
    {
        List<UserProfile> userProfiles = userProfileService.findUserByPrimary(primarySkillSet);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    @GetMapping("/find/{secondarySkillSet}")
    public ResponseEntity<List<UserProfile>> getUserBySecondary(@PathVariable("secondarySkillSet") String secondarySkillSet)
    {
        List<UserProfile> userProfiles = userProfileService.findUserBySecondary(secondarySkillSet);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }


    @PostMapping("/addUserProfile")
    public ResponseEntity<Map<String, String>> addUserProfile(@RequestBody Map<String, Object> userMap)
    {
        String userId = (String) userMap.get("userId");
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String primarySkillSet = (String) userMap.get("primarySkillSet");
        String secondarySkillSet = (String) userMap.get("secondarySkillSet");
        String experience = (String) userMap.get("experience");
        String designation = (String) userMap.get("designation");
        String joiningPeriod = (String) userMap.get("joiningPeriod");
        String location = (String) userMap.get("location");

        UserProfile userProfile = userProfileService.addUserProfile(userId, firstName, lastName, primarySkillSet, secondarySkillSet, experience, designation, joiningPeriod, location);
        return new ResponseEntity<>(generateUserProfileAddedResponse(userProfile), HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile userProfile)
    {
        UserProfile updateUser = userProfileService.updateUserProfile(userProfile);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PutMapping("/updateUserLastInterview")
    public ResponseEntity<Map<String, String>> updateUserLastInterview(@RequestBody Map<String, Object> userMap) throws ParseException {
        String userId = (String) userMap.get("userId");
        String  lastInterview = (String) userMap.get("lastInterview");

        UserProfile userProfile = userProfileService.updateUserLastInterview(userId, lastInterview);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        userProfileService.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Map<String, String> generateUserProfileAddedResponse(UserProfile userProfile)
    {
        Map<String , String> map= new HashMap<>();
        map.put("message", "User Details added!");
        map.put("userId", userProfile.getUserId());
        return map;
    }
}
