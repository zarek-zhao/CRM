package com.zarek.crm.settings.service.impl;

import com.zarek.crm.exception.LoginException;
import com.zarek.crm.settings.dao.UserDao;
import com.zarek.crm.settings.domain.User;
import com.zarek.crm.settings.service.UserService;
import com.zarek.crm.utils.DateTimeUtil;
import com.zarek.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<String, String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //map.put("ip",ip);
        User user = userDao.login(map);
        if(user==null){
            throw new LoginException("账号密码错误");
        }
        //如果程序能够成功的执行到此处说明账号密码正确
        //需要继续向下验证其他3项信息

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime) < 0){
            throw new LoginException("账号已经过期,请联系管理员");
        }

        //判断锁定状态
        String lockState = user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("账号已经被锁定，请联系管理员");
        }

        //判断ip地址
        String allowIps = user.getAllowIps();
        if(allowIps != null && allowIps != ""){
            if(!allowIps.contains(ip)){
                throw new LoginException("ip地址收到限制，请联系管理员");
            }
        }

        return user;
    }

    @Override
    public List<User> getUserList() {

        List<User> uList = userDao.getUserList();

        return uList;
    }
}
