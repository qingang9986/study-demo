package com.qingang.rediscache.service;

import com.qingang.rediscache.RediscacheApplication;
import com.qingang.rediscache.model.User;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: QinGang
 * @date 2021-04-06
 */
@Slf4j
@SpringBootTest(classes = RediscacheApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void getById() {
        User user = userService.getById(1L);
        System.out.println("第一次查询：" + user);

        User user1 = userService.getById(1L);
        System.out.println("第二次查询：" + user1);
    }

}
