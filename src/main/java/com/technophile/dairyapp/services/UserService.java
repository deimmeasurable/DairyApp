package com.technophile.dairyapp.services;

import com.technophile.dairyapp.dto.UpdateUserDTO;
import com.technophile.dairyapp.dto.UserDTO;
import com.technophile.dairyapp.model.DiaryApp;
import com.technophile.dairyapp.requests.CreateAccountRequest;

public interface UserService {
   UserDTO createAccount(CreateAccountRequest accountRequestDTO);

    String findUserByEmail(CreateAccountRequest createAccount);

    UserDTO findUser(String id);

    String updateUser(String id, UpdateUserDTO updateUserDTO);

    DiaryApp addNewDiary(String id, DiaryApp dairyapp);

    void deleteUserByEmail(String email);
}
