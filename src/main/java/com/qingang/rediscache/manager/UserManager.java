package com.qingang.rediscache.manager;

import com.qingang.rediscache.dao.UserMapper;
import com.qingang.rediscache.model.User;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author: QinGang
 * @date 2021-04-06
 */
@Component
@CacheConfig(cacheNames = "user")
public class UserManager {

    @Resource
    private UserMapper userMapper;

    public void insert(User user) {
        userMapper.insert(user);
    }

    @Cacheable(key = "#id", unless = "#result == null")
    public User getById(Long id) {
        System.out.println(123);
        return userMapper.getById(id);
    }

    @CacheEvict(key = "user.id")
    public void updateById(User user) {
        userMapper.updateById(user);
    }
}
