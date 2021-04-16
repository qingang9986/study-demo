package com.qingang.rediscache.common;

import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis客户端操作
 *
 * @author: QinGang
 * @date 2021-04-15
 */
@Component
public class RedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private CacheConfigProperties cacheConfigProperties;

    /**
     * 追加缓存前缀
     *
     * @param key 原始key
     * @return 追加前缀后的key
     */
    public String buildKey(String key) {
        return cacheConfigProperties.getKeyPrefix() + ":" + key;
    }

    /**
     * 分布式锁
     *
     * @param key     key值
     * @param timeout 失效时间 单位：毫秒
     * @return 是否获取到
     */
    public boolean lock(String key, long timeout) {
        String lock = this.buildKey(key);
        // 执行获取锁
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long currentTime = System.currentTimeMillis();
            long expireAt = currentTime + timeout;
            Boolean acquire = connection
                .setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());

            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    // 如果锁已经过期
                    if (expireTime < currentTime) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection
                            .getSet(lock.getBytes(), String.valueOf(expireAt).getBytes());
                        return Long.parseLong(new String(oldValue)) < currentTime;
                    }
                }
            }
            return false;
        });
    }




}
