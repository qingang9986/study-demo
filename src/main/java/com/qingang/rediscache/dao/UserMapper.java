package com.qingang.rediscache.dao;

import com.qingang.rediscache.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getById(Long id);

    void deleteById(Long id);

    void insert(User user);

    void updateById(User user);
}