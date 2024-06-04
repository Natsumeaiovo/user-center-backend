package com.serein.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.serein.usercenter.service.UserService;
import com.serein.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.serein.usercenter.model.domain.User;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author serein
* @description 用户服务
* @createDate 2024-06-04 22:24:35
*/

/**
 * 用户服务实现类
 * @author serein
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }

        // 账户不包含特殊字符
        String invalidPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(invalidPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }

        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }

        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;  // 有人注册了
        }

        // 2. 加密
        final String SALT = "serein";
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;  // 保存失败
        }

        return user.getId();
    }
}




