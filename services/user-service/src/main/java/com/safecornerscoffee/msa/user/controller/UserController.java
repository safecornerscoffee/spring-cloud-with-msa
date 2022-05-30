package com.safecornerscoffee.msa.user.controller;

import com.safecornerscoffee.msa.user.entity.User;
import com.safecornerscoffee.msa.user.vo.RequestUser;
import com.safecornerscoffee.msa.user.vo.ResponseUser;
import com.safecornerscoffee.msa.user.vo.UserDto;
import com.safecornerscoffee.msa.user.exception.UserNotFoundException;
import com.safecornerscoffee.msa.user.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Timed(value="user.controller.post.create", longTask = true)
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        UserDto createdUserDto = userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(createdUserDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping
    @Timed(value="user.controller.get.users", longTask = true)
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<User> userList = userService.getUsers();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{userId}")
    @Timed(value="user.controller.get.user", longTask = true)
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) throws UserNotFoundException {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseUser> delete(@PathVariable("userId") String userId) {
        userService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
