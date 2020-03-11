package com.missz.herbalifegs.service;

import com.missz.herbalifegs.entity.User;
import com.missz.herbalifegs.repository.UserRepository;
import com.missz.herbalifegs.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
