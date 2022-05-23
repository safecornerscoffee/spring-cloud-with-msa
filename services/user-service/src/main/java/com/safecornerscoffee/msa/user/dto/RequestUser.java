package com.safecornerscoffee.msa.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestUser {
    private String email;
    private String password;
    private String username;
}
