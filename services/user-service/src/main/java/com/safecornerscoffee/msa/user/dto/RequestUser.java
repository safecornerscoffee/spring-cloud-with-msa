package com.safecornerscoffee.msa.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class RequestUser {
    private String email;
    private String password;
    private String username;
}
