package com.example.demo.Service.User;

import java.nio.file.AccessDeniedException;

public interface IUserService {
    void registerUser(String username,String password);
    void login(String username,String password) throws AccessDeniedException;
}
