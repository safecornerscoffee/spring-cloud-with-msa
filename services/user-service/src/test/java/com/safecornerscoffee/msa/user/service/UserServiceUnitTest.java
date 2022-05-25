package com.safecornerscoffee.msa.user.service;


import com.safecornerscoffee.msa.user.entity.User;
import com.safecornerscoffee.msa.user.vo.RequestUser;
import com.safecornerscoffee.msa.user.vo.ResponseUser;
import com.safecornerscoffee.msa.user.vo.UserDto;
import com.safecornerscoffee.msa.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserServiceUnitTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void mapUserToUserDTO() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        RequestUser requestUser = new RequestUser();
        requestUser.setEmail("coffee@safecornerscoffee.com");
        requestUser.setUsername("coffee");
        requestUser.setPassword("coffee");

        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        System.out.println(userDto);

        User user = modelMapper.map(userDto, User.class);
        System.out.println(user);

        User savedUser = userRepository.save(user);
        System.out.println(savedUser);

        ResponseUser responseUser = modelMapper.map(savedUser, ResponseUser.class);
        System.out.println(responseUser);
    }
}
