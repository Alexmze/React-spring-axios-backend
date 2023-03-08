package com.amuniz.yt.sbreact.fullstackbackend.controller;

import com.amuniz.yt.sbreact.fullstackbackend.exception.UserNotFoundException;
import com.amuniz.yt.sbreact.fullstackbackend.model.User;
import com.amuniz.yt.sbreact.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/edit/{id}")
    public User editUser(@RequestBody User editedUser, Long id) {
        return userRepository.findById(editedUser.getId())
                .map( user -> {
                    user.setUserName(editedUser.getUserName());
                    user.setName(editedUser.getName());
                    user.setEmail(editedUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User ID " + id + " has been deleted.";
    }
}
