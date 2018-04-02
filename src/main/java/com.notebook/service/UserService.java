package com.notebook.service;

import com.notebook.entity.User;

import java.util.Map;

public interface UserService {
    int insertUser(User user);
    Map verificationName(String userName);
    Map verification(String userName,String password,Boolean autoLogin);
}
