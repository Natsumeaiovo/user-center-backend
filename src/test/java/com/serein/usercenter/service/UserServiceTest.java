package com.serein.usercenter.service;
import java.util.Date;

import com.serein.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务注册
 * @author serein
 */
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testUserRegister() {
        String userAccount = "admin";
        String userPassword = "12345678";
        String checkPassword = "12345678";
        String planetCode = "555";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        System.out.println(result);
    }

    @Test
    void testUserRegisterCondition() {

        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        String planetCode = "666";

        // 密码为空
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        // 用户名小于4位
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        // 密码小于8位
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        // 用户名包含特殊字符
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        // 密码和校验密码不相同
        userAccount = "yupi";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        // 有重复账户(数据库已存在123账户)
        userAccount = "123";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertEquals(-1, result);

        // 注册成功
        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        Assertions.assertTrue(result > 0);
    }
}