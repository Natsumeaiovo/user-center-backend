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
    public void testAddUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("dog111");
        user.setUserAccount("123");
        user.setAvatarUrl("123123");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("1234");
        user.setEmail("56");


        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        // 密码为空
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        // 用户名小于4位
        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        // 密码小于8位
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        // 用户名包含特殊字符
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        // 密码和校验密码不相同
        userAccount = "yupi";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        // 有重复账户(数据库已存在123账户)
        userAccount = "123";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);

        // 注册成功
        userAccount = "yupi";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);
    }
}