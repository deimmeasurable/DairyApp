package com.technophile.dairyapp.controllers;

import com.technophile.dairyapp.exceptions.DiaryAppException;
import com.technophile.dairyapp.requests.CreateAccountRequest;
import com.technophile.dairyapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.technophile.dairyapp.response.ApiResponse;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
   private UserService userService;

    public  UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createNewUserAccount(@RequestBody CreateAccountRequest createNewUserAccount) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .message("id: " + userService.createAccount(createNewUserAccount))
                    .isSuccessful(true)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DiaryAppException e) {
            ApiResponse response = ApiResponse.builder()
                    .message(e.getMessage())
                    .isSuccessful(false)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
