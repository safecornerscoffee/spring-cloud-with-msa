package com.safecornerscoffee.msa.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("could not find user[id=%d]", id));
    }
    public UserNotFoundException(String userId) {
        super(String.format("could not find user[userId=%s]", userId));
    }
}
