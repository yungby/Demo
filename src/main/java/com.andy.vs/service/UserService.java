package com.andy.vs.service;

import com.andy.vs.entity.User;

public interface UserService {
    public User selectByNameAndId(String Id, String Name);
}
