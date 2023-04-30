package com.project.stuckunderflow.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.project.stuckunderflow.exceptions.UserNotFoundException;
import com.project.stuckunderflow.responses.UserResponse;
import com.project.stuckunderflow.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.project.stuckunderflow.entities.User;
import com.project.stuckunderflow.repos.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    };

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}")
    public UserResponse getOneUser(@PathVariable Long userId) {
        User user = userService.getOneUserById(userId);
        if(user == null){
            throw new UserNotFoundException();
        }
        return new UserResponse(user);

    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser) {
        return userService.updateOneUser(userId,newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @GetMapping("/activity/{UserId}")
    public List<Object> getUserActivity(@PathVariable Long userId){
        return userService.getUserActivity(userId);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFound(){
    }
}







