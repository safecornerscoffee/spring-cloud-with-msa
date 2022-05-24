package com.safecornerscoffee.msa.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String password;

    private List<ResponseOrder> orders;
}
