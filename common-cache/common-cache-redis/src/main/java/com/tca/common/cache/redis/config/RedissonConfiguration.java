package com.tca.common.cache.redis.config;

import com.tca.common.cache.redis.properties.RedisProperties;
import com.tca.common.utils.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouan
 * @Date
 */
@Configuration
@Slf4j
public class RedissonConfiguration {

    @Autowired
    private RedisProperties redisProperties;

    @Configuration
    @ConditionalOnClass({Redisson.class})
    public class RedissonClientConfiguration {

        /**
         * 单机模式
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "single", matchIfMissing = true)
        RedissonClient redissonSingle() {
            Config config = new Config();
            String node = redisProperties.getHost() + ":" + redisProperties.getPort();
            node = node.startsWith("redis://") ? node : "redis://" + node;
            SingleServerConfig serverConfig = config.useSingleServer()
                    .setAddress(node)
                    .setTimeout(redisProperties.getTimeout())
                    .setConnectionPoolSize(redisProperties.getLettuce().getPool().getSize())
                    .setConnectionMinimumIdleSize(redisProperties.getLettuce().getPool().getMinIdle());
            if (ValidateUtils.isNotEmpty(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }
            return Redisson.create(config);
        }


        /**
         * 集群模式
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "cluster")
        RedissonClient redissonCluster() {
            Config config = new Config();
            List<String> nodeList = redisProperties.getCluster().getNodes();
            List<String> newNodes = new ArrayList(nodeList.size());
            nodeList.stream().forEach((node) -> newNodes.add(
                    node.startsWith("redis://") ? node : "redis://" + node));

            ClusterServersConfig serverConfig = config.useClusterServers()
                    .addNodeAddress(newNodes.toArray(new String[newNodes.size()]))
                    .setScanInterval(
                            redisProperties.getCluster().getScanInterval())
                    .setIdleConnectionTimeout(
                            redisProperties.getLettuce().getPool().getConnTimeout())
                    .setConnectTimeout(
                            redisProperties.getLettuce().getPool().getConnTimeout())
                    .setRetryAttempts(
                            redisProperties.getCluster().getRetryAttempts())
                    .setRetryInterval(
                            redisProperties.getCluster().getRetryInterval())
                    .setMasterConnectionPoolSize(
                            redisProperties.getCluster().getMasterConnectionPoolSize())
                    .setSlaveConnectionPoolSize(
                            redisProperties.getCluster().getSlaveConnectionPoolSize())
                    .setTimeout(redisProperties.getTimeout());
            if (ValidateUtils.isNotEmpty(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }
            if (ValidateUtils.isNotEmpty(redisProperties.getCluster().getPassword())) {
                serverConfig.setPassword(redisProperties.getCluster().getPassword());
            }
            return Redisson.create(config);
        }

        /**
         * 哨兵模式
         * @return
         */
        @Bean
        @ConditionalOnProperty(name = "spring.redis.mode", havingValue = "sentinel")
        RedissonClient redissonSentinel() {
            Config config = new Config();
            List<String> nodeList = redisProperties.getSentinel().getNodes();
            List<String> newNodes = new ArrayList(nodeList.size());
            nodeList.stream().forEach((node) -> newNodes.add(
                    node.startsWith("redis://") ? node : "redis://" + node));

            SentinelServersConfig serverConfig = config.useSentinelServers()
                    .addSentinelAddress(newNodes.toArray(new String[newNodes.size()]))
//                    .setCheckSentinelsList(false)
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .setReadMode(ReadMode.SLAVE)
                    .setTimeout(redisProperties.getTimeout());

            if (StringUtils.isNotBlank(redisProperties.getPassword())) {
                serverConfig.setPassword(redisProperties.getPassword());
            }
            if (ValidateUtils.isNotEmpty(redisProperties.getSentinel().getPassword())) {
                serverConfig.setPassword(redisProperties.getSentinel().getPassword());
            }
            return Redisson.create(config);
        }
    }
}
