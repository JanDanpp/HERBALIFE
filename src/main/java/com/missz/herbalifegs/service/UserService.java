package com.missz.herbalifegs.service;

import com.missz.herbalifegs.entity.User;

public interface UserService {

    User checkUser(String username,String password);
}
