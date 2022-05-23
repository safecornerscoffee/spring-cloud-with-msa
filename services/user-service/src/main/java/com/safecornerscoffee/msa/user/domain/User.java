package com.safecornerscoffee.msa.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String username;
}
