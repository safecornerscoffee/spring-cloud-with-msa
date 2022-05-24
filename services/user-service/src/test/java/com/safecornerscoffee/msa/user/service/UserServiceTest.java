package com.safecornerscoffee.msa.user.service;

import com.safecornerscoffee.msa.user.domain.User;
import com.safecornerscoffee.msa.user.dto.ResponseUser;
import com.safecornerscoffee.msa.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Test
    void createUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("coffee@safecornerscoffee.cmom");
        userDto.setPassword("coffee");
        userDto.setUsername("coffee");

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = mapper.map(userDto, User.class);
        System.out.println(user);

        UserDto savedUserDto = mapper.map(user, UserDto.class);
        System.out.println(savedUserDto);

        ResponseUser responseUser = mapper.map(savedUserDto, ResponseUser.class);
        System.out.println(responseUser);
    }
}