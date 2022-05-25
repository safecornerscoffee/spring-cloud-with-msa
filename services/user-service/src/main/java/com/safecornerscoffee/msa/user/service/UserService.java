package com.safecornerscoffee.msa.user.service;

import com.safecornerscoffee.msa.user.entity.User;
import com.safecornerscoffee.msa.user.vo.ResponseOrder;
import com.safecornerscoffee.msa.user.vo.UserDto;
import com.safecornerscoffee.msa.user.exception.UserNotFoundException;
import com.safecornerscoffee.msa.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                true, true, true, true,
                new ArrayList<>()
        );
    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

    public UserDto getUserById(String userId) throws UserNotFoundException {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user, UserDto.class);

        List<ResponseOrder> orderList = new ArrayList<>();

        userDto.setOrders(orderList);

        return userDto;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserDto.class);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }
}
