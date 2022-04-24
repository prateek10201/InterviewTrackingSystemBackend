package com.backend.ITS03.service;

import com.backend.ITS03.entity.InterviewSchedule;
import com.backend.ITS03.entity.UserCredentials;
import com.backend.ITS03.entity.UserProfile;
import com.backend.ITS03.exception.UserNotFoundException;
import com.backend.ITS03.repository.InterviewScheduleRepository;
import com.backend.ITS03.repository.UserCredentialsRepository;
import com.backend.ITS03.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public List<UserProfile> findAllUserProfiles()
    {
        return userProfileRepository.findAll();
    }

    public UserProfile findUserByUserId(String userId)
    {
        return userProfileRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User by "+userId+" is not found"));
    }

    public List<UserProfile> findUserByDesignation(String designation)
    {
        return userProfileRepository.findUserByDesignation(designation).orElseThrow(() -> new UserNotFoundException("User by "+designation+" is not found"));
    }

    public List<UserProfile> findUserByPrimary(String primarySkillSet)
    {
        return userProfileRepository.findUserByPrimarySkillSet(primarySkillSet).orElseThrow(() -> new UserNotFoundException("User by "+primarySkillSet+" is not found"));
    }

    public List<UserProfile> findUserBySecondary(String secondarySkillSet)
    {
        return userProfileRepository.findUserBySecondarySkillSet(secondarySkillSet).orElseThrow(() -> new UserNotFoundException("User by "+secondarySkillSet+" is not found"));
    }

    public UserProfile addUserProfile(String userId, String firstName, String lastName, String primarySkillSet, String secondarySkillSet, String experience, String designation, String joiningPeriod, String location) throws UserNotFoundException
    {
        if(userId == null || firstName == null || lastName == null || primarySkillSet == null || secondarySkillSet == null || experience == null || designation == null || joiningPeriod == null || location == null)
            throw new UserNotFoundException("Fields can't be empty!");

        userProfileRepository.findUserByUserId(userId)
                .ifPresent(u -> {
                    throw new UserNotFoundException("User already exists!");
                });

        UserCredentials userCredentials = userCredentialsRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User with userId "+userId+" doesnt exist"));

        UserProfile userProfile = new UserProfile(userId, UUID.randomUUID().toString(), firstName, lastName, primarySkillSet, secondarySkillSet, experience, designation, joiningPeriod, location, false, null);
        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserLastInterview(String userId, String lastInterview) throws ParseException {
        if(userId == null || lastInterview == null)
            throw new UserNotFoundException("Fields cannot be null!");

//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        Date lastDateInterview = format.parse(lastInterview);

        UserProfile userProfile = userProfileRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User by "+userId+" is not found"));
        userProfile.setLastInterview(lastInterview);
        return userProfileRepository.save(userProfile);
    }

    public UserProfile updateUserProfile(UserProfile userProfile)
    {
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Long id)
    {
        userProfileRepository.deleteUserById(id);
    }


}
