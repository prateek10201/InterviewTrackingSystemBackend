package com.backend.ITS03.service;

import com.backend.ITS03.Constants;
import com.backend.ITS03.entity.InterviewSchedule;
import com.backend.ITS03.entity.UserCredentials;
import com.backend.ITS03.entity.UserProfile;
import com.backend.ITS03.exception.UserNotFoundException;
import com.backend.ITS03.repository.InterviewScheduleRepository;
import com.backend.ITS03.repository.UserProfileRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InterviewScheduleService {

    @Autowired
    private InterviewScheduleRepository interviewScheduleRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<InterviewSchedule> findAllInterviews()
    {
        return interviewScheduleRepository.findAll();
    }

    public InterviewSchedule findCandidateRating(String userId)
    {
        if(userId == null)
            throw new UserNotFoundException("Field cannot be empty");
        return interviewScheduleRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User by "+userId+" is not found"));
    }

    public UserProfile fetchCandidateData(String userId)
    {
        return userProfileRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User by "+userId+" is not found"));
    }

    public InterviewSchedule findUserRating(String userId)
    {
        return interviewScheduleRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User by "+userId+" is not found"));
    }

//    public InterviewSchedule addInterview(String userId, String candidateId, String interviewType, String interviewDate, String rating) throws UserNotFoundException{
//        if(userId == null || interviewType == null || interviewDate == null || rating == null || candidateId == null)
//            throw new UserNotFoundException("Fields cannot be empty");
//
//       UserProfile userProfile = userProfileRepository.findUserByUserId(userId).orElseThrow(() -> new UserNotFoundException("User by "+userId+" is not found"));
//
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        Date interviewScheduledDate = format.parse(interviewDate);
//
//        if(interviewScheduledDate.getTime() - userProfile.getLastInterview().getTime() < Constants.TIME_DURATION)
//            throw new UserNotFoundException("Interview can only be scheduled only after 30 days of previous interview");
//        InterviewSchedule interviewSchedule = new InterviewSchedule(UUID.randomUUID().toString(), candidateId, userId, interviewType, interviewDate, rating);
//        userProfile.setInterviewStatus(true);
//        userProfileRepository.save(userProfile);
//        return interviewScheduleRepository.save(interviewSchedule);
//    }

    public InterviewSchedule addInterview(String userId, String candidateId, String interviewType, String interviewDate, String rating)
    {
        if(userId == null || interviewType == null || interviewDate == null || rating == null || candidateId == null)
            throw new UserNotFoundException("Fields cannot be empty");

        InterviewSchedule interviewSchedule = new InterviewSchedule(UUID.randomUUID().toString(), candidateId, userId, interviewType, interviewDate, rating);
        return interviewScheduleRepository.save(interviewSchedule);
    }

    public InterviewSchedule updateInterview(InterviewSchedule interviewSchedule)
    {
        return interviewScheduleRepository.save(interviewSchedule);
    }

    public void deleteInterview(Long id)
    {
        interviewScheduleRepository.deleteInterviewById(id);
    }
}
