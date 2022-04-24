package com.backend.ITS03.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.backend.ITS03.Constants;
import com.backend.ITS03.entity.UserCredentials;
import com.backend.ITS03.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/userCredentials")
public class UserCredentialsController {

    @Autowired
    private UserCredentialsService userCredentialsService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserCredentials>> getAllUsers()
    {
        List<UserCredentials> userCredentials = userCredentialsService.findAllUsers();
        return new ResponseEntity<>(userCredentials, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserCredentials> getUsersById(@PathVariable("id") Long id)
    {
        UserCredentials userCredential = userCredentialsService.findUserById(id);
        return new ResponseEntity<>(userCredential, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Map<String, String>> addUser(@RequestBody Map<String, Object> userMap)
    {
        String userId = (String) userMap.get("userId");
        String password = (String) userMap.get("password");
        String userType = "";
        boolean loginStatus = false;
        UserCredentials userCredentials = userCredentialsService.addUser(userId, password, userType, loginStatus);
        return new ResponseEntity<>(generateJWTSignUpToken(userCredentials), HttpStatus.OK);
    }

    @PostMapping("/signUpUser")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap)
    {
        String userId = (String) userMap.get("userId");
        String password = (String) userMap.get("password");
        String userType = (String) userMap.get("userType");

        UserCredentials userCredentials = userCredentialsService.signUpUser(userId, password, userType);
        return new ResponseEntity<>(generateJWTLoginToken(userCredentials), HttpStatus.OK);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserCredentials> updateUser(@RequestBody UserCredentials userCredentials)
    {
        UserCredentials updateUser = userCredentialsService.updateUser(userCredentials);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id)
    {
        userCredentialsService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Map<String, String> generateJWTSignUpToken(UserCredentials userCredentials)
    {
        Map<String, String > map = new HashMap<>();
        long issued_at = System.currentTimeMillis();
        Algorithm algorithm = Algorithm.HMAC256(Constants.API_SECRET_KEY);

        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userId",userCredentials.getUserId())
                .withClaim("userType",userCredentials.getUserType())
                .withIssuedAt(new Date(issued_at))
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.TOKEN_VALIDITY))
                .sign(algorithm);

        map.put("message", "User added!");
        map.put("userId",userCredentials.getUserId());
        map.put("token",token);
        return map;
    }

    private Map<String, String> generateJWTLoginToken(UserCredentials userCredentials)
    {
        Map<String, String > map = new HashMap<>();
        long issued_at = System.currentTimeMillis();
        Algorithm algorithm = Algorithm.HMAC256(Constants.API_SECRET_KEY);

        String token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userId",userCredentials.getUserId())
                .withClaim("userType",userCredentials.getUserType())
                .withIssuedAt(new Date(issued_at))
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.TOKEN_VALIDITY))
                .sign(algorithm);

        map.put("message", "User Signed In!");
        map.put("userId",userCredentials.getUserId());
        map.put("token",token);
        return map;
    }
}
