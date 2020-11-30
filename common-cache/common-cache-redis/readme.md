#### 1.使用spring-boot-redis中的redisTemplate, 作基本操作

#### 2.使用redisson作分布式锁
    2.1 如果redis使用的 cluster 或 sentinel, 需要添加配置 spring.redis.mode=sentinel/cluster
    2.2 为什么选择redisson
        2.2.1 使用原生redis实现分布式锁较为复杂, 需要考虑锁的基本功能, 锁超时和锁释放, 阻塞和非阻塞, 可重入性, 高可用等实际场景
        2.2.2 redisson的实现考虑了上述场景, 开源且有很多一线互联网公司使用, 得到过验证