package com.technophile.dairyapp.mappers;

import com.technophile.dairyapp.dto.UserDTO;
import com.technophile.dairyapp.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO userTOUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
