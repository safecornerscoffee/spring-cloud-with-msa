package com.safecornerscoffee.msa.user.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String userId;
    private String email;
    private String username;
    private String password;
    private Date createdAt;

    private List<ResponseOrder> orders;
}
