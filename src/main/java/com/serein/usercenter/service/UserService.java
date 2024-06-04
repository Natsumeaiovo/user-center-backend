package com.serein.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.serein.usercenter.model.domain.User;


/**
* @author serein
* @description 用户服务
* @createDate 2024-06-04 22:24:35
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
