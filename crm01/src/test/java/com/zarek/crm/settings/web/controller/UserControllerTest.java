package com.zarek.crm.settings.web.controller;

import org.junit.Test;

public class UserControllerTest {

    @Test
    public void test01(){
        System.out.println("test UserControllerTest");
//        String expireTime = "2039-10-10 10:10:10";
//        String currentTime = DateTimeUtil.getSysTime();
//        System.out.println(currentTime);
//        int count = expireTime.compareTo(currentTime);
//        System.out.println(count);

//        String lockState = "0";
//        if("0".equals(lockState)){
//            System.out.println("账号已经锁定");//之后通过抛出异常的方式
//        }

        String ip = "192.168.1.13";
        String allowIp = "192.168.1.12,192.168.1.13,192.168.3.12";
        if(allowIp.contains(ip)){
            System.out.println("允许访问");
        }else{
            System.out.println("ip访问受限");
        }
    }
}