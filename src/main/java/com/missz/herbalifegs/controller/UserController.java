package com.missz.herbalifegs.controller;

import com.missz.herbalifegs.entity.User;
import com.missz.herbalifegs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/d")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }

    @GetMapping("/user")
    public User inserUser(User user){
        User save = userRepository.save(user);
        return save;
    }

}
