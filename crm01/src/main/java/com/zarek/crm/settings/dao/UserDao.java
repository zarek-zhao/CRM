package com.zarek.crm.settings.dao;

import com.zarek.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String,String> map);
}
