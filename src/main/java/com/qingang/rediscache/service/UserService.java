package com.qingang.rediscache.service;

import com.qingang.rediscache.model.User;

/**
 * @author: QinGang
 * @date 2021-04-06
 */
public interface UserService {

    void insert(User user);

    User getById(Long id);

    void updateById(User user);

}
