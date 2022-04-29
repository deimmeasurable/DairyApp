package com.technophile.dairyapp.services;

import com.technophile.dairyapp.dto.UserDTO;
import com.technophile.dairyapp.model.User;
import com.technophile.dairyapp.repositories.UserRepository;
import com.technophile.dairyapp.requests.CreateAccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
@ContextConfiguration(classes ={DataConfig.class})
@ComponentScan(basePackages = "com.technophiles.diaryapp.**")
public class UserServiceMockitoTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService = new UserServiceImplementation();

    @BeforeEach
    void setup() {

    }

    @Test
    void testThatUserCanCreated() {

     CreateAccountRequest   createAccountRequest = CreateAccountRequest.builder()
                .email("test@gmail.com")
                .password("password")
                .build();
     //when
        User user = new User("test@gmail.com","password","dummy id");
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());

        when(userRepository.save(any(User.class))).thenReturn(user);

          UserDTO userDTO= userService.createAccount(createAccountRequest);
          assertThat(userDTO.getId()).isEqualTo("dummy id");
          assertThat(userDTO.getEmail()).isEqualTo("test@gmail.com");



    }
}
