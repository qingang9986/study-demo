package com.qingang.rediscache.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: QinGang
 * @date 2021-04-16
 */
@ConfigurationProperties(prefix = "spring.cache")
@Component
@Data
public class CacheConfigProperties {

    private String keyPrefix;

}
