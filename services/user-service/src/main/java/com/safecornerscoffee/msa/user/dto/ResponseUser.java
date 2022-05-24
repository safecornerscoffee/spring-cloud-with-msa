package com.safecornerscoffee.msa.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private Long id;
    private String email;
    private String username;
    private String password;

    private List<ResponseOrder> orders;
}
