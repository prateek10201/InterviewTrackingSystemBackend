package com.backend.ITS03.service;

import com.backend.ITS03.entity.UserCredentials;
import com.backend.ITS03.exception.UserNotFoundException;
import com.backend.ITS03.repository.UserCredentialsRepository;
import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public List<UserCredentials> findAllUsers()
    {
        return userCredentialsRepository.findAll();
    }

    public UserCredentials addUser(String userId, String password, String userType, Boolean loginStatus) throws UserNotFoundException
    {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));

        if(userId == null || password == null) throw new UserNotFoundException("Fields can't be empty");

        userCredentialsRepository.findUserByUserId(userId)
                .ifPresent(u -> {
                    throw new UserNotFoundException("User already exists!");
                });
        UserCredentials userCredentials = new UserCredentials(userType, userId, hashedPassword, loginStatus);
        return userCredentialsRepository.save(userCredentials);
    }

    public UserCredentials updateUser(UserCredentials userCredentials)
    {
        return userCredentialsRepository.save(userCredentials);
    }

    public UserCredentials findUserById(Long id)
    {
        return userCredentialsRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User by id "+id+" is not found"));
    }

    public void deleteUser(Long id)
    {
        userCredentialsRepository.deleteUserById(id);
    }

    public UserCredentials signUpUser(String userId, String password, String userType) throws UserNotFoundException
    {
       if(userId == null || password == null || userType == null)
           throw new UserNotFoundException("Fields cannot be null");

       userCredentialsRepository.findUserByUserId(userId)
               .ifPresentOrElse(userCredentials -> {
                   if (!BCrypt.checkpw(password, userCredentials.getPassword()))
                       throw new UserNotFoundException("Invalid password");
               }, () -> {
                   throw new UserNotFoundException("Invalid userId");
               });

       UserCredentials userCredentials = userCredentialsRepository.findUserByUserId(userId).get();
       userCredentials.setUserType(userType);
       userCredentials.setLoginStatus(true);
       return userCredentialsRepository.save(userCredentials);
    }
}
