package com.safecornerscoffee.msa.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;
}
