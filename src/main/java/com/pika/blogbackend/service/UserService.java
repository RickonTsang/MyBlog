package com.pika.blogbackend.service;

import com.pika.blogbackend.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
