package com.technophile.dairyapp.services;

import com.technophile.dairyapp.dto.UpdateUserDTO;
import com.technophile.dairyapp.dto.UserDTO;
import com.technophile.dairyapp.exceptions.DiaryAppException;
import com.technophile.dairyapp.mappers.UserMapper;
import com.technophile.dairyapp.mappers.UserMapperImpl;
import com.technophile.dairyapp.model.DiaryApp;
import com.technophile.dairyapp.model.User;
import com.technophile.dairyapp.requests.CreateAccountRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class UserServiceImplementationTest {
    @Autowired
    private UserService userService;

    //    @Autowired
    UserMapper userMapper = new UserMapperImpl();

    private CreateAccountRequest createAccountRequest;


    @BeforeEach
    void setup() {
         createAccountRequest = CreateAccountRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();

    }

    @Test
    void testCreateAccount() {
        UserDTO userDTO = userService.createAccount(createAccountRequest);
        assertThat(userDTO.getId()).isNotNull();
        assertThat(userDTO.getEmail()).isEqualTo("test@gmail.com");

    }

    @Test
    void testThatOtherUserCanNotCraeteAccountWithSameEmail_throws_Exception() {
        userService.createAccount(createAccountRequest);
        CreateAccountRequest createAccount2 = CreateAccountRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();
//        assertThrows(DiaryAppException.class,()->userService.createAccount(createAccount2));
        assertThatThrownBy(() -> userService.createAccount(createAccount2)).isInstanceOf(DiaryAppException.class).hasMessage("user already exist");

    }

    @Test
    void testThatUserCanBeFoundedByEmail() {
        String id = "626c124c361ee65325a7cf3b";
        UserDTO userDTO = userService.findUser(id);
        assertThat(userDTO.getId()).isEqualTo("626c124c361ee65325a7cf3b");

    }

    @Test
    void testThatCanUpdateUserInformation() {
        UserDTO userDTO = userService.createAccount(createAccountRequest);
        UpdateUserDTO updateUser = new UpdateUserDTO("", "gorrila");
        String update = userService.updateUser(userDTO.getId(), updateUser);
        assertThat(update).isEqualTo("user details update successfully");

        UserDTO userFromDatabase = userService.findUser(userDTO.getId());
        assertThat(userFromDatabase.getEmail()).isEqualTo("test@gmail.com");

    }

    @Test
    void testThatThrowsExceptionWhenUserIdisNotFound() {
        userService.createAccount(createAccountRequest);
        String id = "null id";
        UpdateUserDTO userDTO = UpdateUserDTO.builder()
                .email("test@gmail.com")
                .password("gorrila")
                .build();
        assertThatThrownBy(() -> userService.updateUser(id, userDTO)).isInstanceOf(DiaryAppException.class).hasMessage("user account does not exist");
    }

    @Test
    void testThatUserCanAddDiaryToUser() {
        UserDTO userCreated= userService.createAccount(createAccountRequest);
        UserDTO userDTO = userService.findUser(userCreated.getId());
        User user = userMapper.userDTOToUser(userDTO);
        String dairyTitle = "new diary";
        DiaryApp dairyapp = new DiaryApp(dairyTitle, user);
        DiaryApp savedDairy = userService.addNewDiary(userDTO.getId(), dairyapp);
//        assertThat()

    }


    @AfterEach
    void tearDown() {
        userService.deleteUserByEmail("test@gmail.com");
    }
}