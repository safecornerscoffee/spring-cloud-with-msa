package com.safecornerscoffee.msa.user.service;

import com.safecornerscoffee.msa.user.domain.User;
import com.safecornerscoffee.msa.user.dto.ResponseOrder;
import com.safecornerscoffee.msa.user.dto.UserDto;
import com.safecornerscoffee.msa.user.exception.UserNotFoundException;
import com.safecornerscoffee.msa.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

    public UserDto getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user, UserDto.class);

        List<ResponseOrder> orderList = new ArrayList<>();

        userDto.setOrders(orderList);

        return userDto;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
