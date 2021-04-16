package com.qingang.rediscache.service.impl;

import com.qingang.rediscache.manager.UserManager;
import com.qingang.rediscache.model.User;
import com.qingang.rediscache.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author: QinGang
 * @date 2021-04-06
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserManager userManager;

    @Override
    public void insert(User user) {
        userManager.insert(user);
    }

    @Override
    public User getById(Long id) {
        return userManager.getById(id);
    }

    @Override
    public void updateById(User user) {
        userManager.updateById(user);
    }
}
