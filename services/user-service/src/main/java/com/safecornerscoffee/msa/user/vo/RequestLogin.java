package com.safecornerscoffee.msa.user.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RequestLogin {

    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
