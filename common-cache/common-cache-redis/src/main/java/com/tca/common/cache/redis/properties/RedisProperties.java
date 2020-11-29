package com.tca.common.cache.redis.properties;

import com.tca.common.cache.redis.enums.RedisModeEnum;
import com.tca.common.utils.ValidateUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.config.ReadMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

/**
 * @author zhouan
 * @Date 2020/11/25
 * redis配置
 * @see org.springframework.boot.autoconfigure.data.redis.RedisProperties
 */
@Data
@Component
@Slf4j
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host = "127.0.0.1";

    private Integer port = 6379;

    private Integer database = 0;

    private String password;

    private Integer timeout = 3000;

    private String mode = RedisModeEnum.SINGLE.getMode();

    private Lettuce lettuce = new Lettuce();

    private Sentinel sentinel;

    private Cluster cluster;

    /**
     * lettuce 配置
     */
    @Data
    public static class Lettuce {

        private Duration shutdownTimeout = Duration.ofMillis(100L);

        private Pool pool = new Pool();
    }

    /**
     * pool 配置
     */
    @Data
    public static class Pool {

        private Integer maxIdle = 16;

        private Integer minIdle = 8;

        private Integer maxActive = 8;

        private Integer size = 10;

        private Integer connTimeout = 3000;

        private Integer soTimeout = 3000;

    }

    /**
     * sentinel 配置
     */
    @Data
    public static class Sentinel {

        /**
         * 哨兵master 名称
         */
        private String master;

        /**
         * 哨兵节点
         */
        private List<String> nodes;

        /**
         * 哨兵配置
         */
        private boolean masterOnlyWrite = true;

        /**
         * 最大重试
         */
        private Integer failMax = 3;

        /**
         * password
         */
        private String password;

    }

    /**
     * cluster
     */
    @Data
    public static class Cluster {

        /**
         * 集群节点
         */
        private List<String> nodes;


        private Integer maxRedirects;

        /**
         * 集群状态扫描间隔时间，单位是毫秒
         */
        private Integer scanInterval = 1000;

        /**
         * SLAVE - 只在从服务节点里读取(默认)
         * MASTER - 只在主服务节点里读取
         * MASTER_SLAVE - 在主从服务节点里都可以读取
         */
        private String readMode = ReadMode.SLAVE.name();

        /**
         * (从节点连接池大小) 默认值:64
         */
        private Integer slaveConnectionPoolSize = 64;

        /**
         * (主节点连接池大小)默认值:64
         */
        private Integer masterConnectionPoolSize = 64;

        /**
         * (命令失败重试次数) 默认值:3
         */
        private Integer retryAttempts = 3;

        /**
         * 执行失败最大次数默认值:3
         */
        private Integer failedAttempts = 3;

        /**
         *命令重试发送时间间隔,单位:毫秒 默认值:1500
         */
        private Integer retryInterval = 1500;

        private String password;
    }
}
