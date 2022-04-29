package com.technophile.dairyapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {
    @NotNull @NotBlank
    private String email;
    @NotNull @NotBlank
    private String password;
}
