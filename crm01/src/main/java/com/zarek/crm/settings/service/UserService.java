package com.zarek.crm.settings.service;

import com.zarek.crm.exception.LoginException;
import com.zarek.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
