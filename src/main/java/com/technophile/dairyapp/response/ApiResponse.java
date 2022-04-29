package com.technophile.dairyapp.response;

import com.technophile.dairyapp.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse {
    private UserDTO payLoad;
    private String message;
    private Boolean isSuccessful;
}
