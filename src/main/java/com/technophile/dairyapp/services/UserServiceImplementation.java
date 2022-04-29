package com.technophile.dairyapp.services;

import com.technophile.dairyapp.dto.UpdateUserDTO;
import com.technophile.dairyapp.dto.UserDTO;
import com.technophile.dairyapp.exceptions.DiaryAppException;
import com.technophile.dairyapp.mappers.UserMapper;
import com.technophile.dairyapp.mappers.UserMapperImpl;
import com.technophile.dairyapp.model.DiaryApp;
import com.technophile.dairyapp.model.User;
import com.technophile.dairyapp.repositories.DiaryRepository;
import com.technophile.dairyapp.repositories.UserRepository;
import com.technophile.dairyapp.requests.CreateAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    private UserMapperImpl userMapper = new UserMapperImpl();

    @Autowired
    private DiaryRepository diaryRepository;



    //    public UserServiceImplementation(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
    @Override
    public UserDTO createAccount(CreateAccountRequest accountRequestDTO) {
        User user = new User();

        user.setEmail(accountRequestDTO.getEmail());
        user.setPassword(accountRequestDTO.getPassword());
        user.setDairyApp(new HashSet<>());

        Optional<User> optionalUser = userRepository.findUserByEmail(accountRequestDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new DiaryAppException("user already exist");
        }
        User savedUser = userRepository.save(user);

        return userMapper.userTOUserDTO(savedUser);
    }

    @Override
    public String findUserByEmail(CreateAccountRequest createAccount) {
        User user = new User();
        user.setEmail(createAccount.getEmail());
        user.setPassword(createAccount.getPassword());
        userRepository.save(user);
        userRepository.findUserByEmail(createAccount.getEmail());
        return user.getEmail();
    }

    @Override
    public UserDTO findUser(String id) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new DiaryAppException("user does not exist"));


        return userMapper.userTOUserDTO(user);
    }

    @Override
    public String updateUser(String id, UpdateUserDTO userTOUserDTO) {
        boolean isUpdated = false;
        User user = userRepository.findUserById(id).orElseThrow(() -> new DiaryAppException("user account does not exist"));
        if (!(userTOUserDTO.getEmail() == null || userTOUserDTO.getEmail().equals(""))) {

            user.setEmail(userTOUserDTO.getEmail());
            isUpdated = true;
        }
        if (!(userTOUserDTO.getPassword() == null || userTOUserDTO.getPassword().equals(""))) {


            user.setPassword(userTOUserDTO.getPassword());
            isUpdated = true;
        }
        if (isUpdated) {


            userRepository.save(user);

        }
        return "user details update successfully";
    }

    @Override
    public DiaryApp addNewDiary(String id, DiaryApp dairyapp) {
        User user = userRepository.findUserById(id).orElseThrow(() -> new DiaryAppException("user does not exist"));
        DiaryApp savedDiary = diaryRepository.save(dairyapp);
        user.getDairyApp().add(savedDiary);
        userRepository.save(user);

        return savedDiary;
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(()-> new DiaryAppException("user not found "));
        userRepository.delete(user);

    }
}