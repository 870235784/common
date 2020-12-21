#### 1.使用spring-boot-redis中的redisTemplate, 作基本操作

#### 2.使用redisson作分布式锁
    如果redis使用的 cluster 或 sentinel, 需要添加配置 spring.redis.mode=sentinel/cluster
    为什么选择redisson
        使用原生redis实现分布式锁较为复杂, 需要考虑锁的基本功能, 锁超时和锁释放, 阻塞和非阻塞, 可重入性, 高可用等实际场景
        redisson的实现考虑了上述场景, 开源且有很多一线互联网公司使用, 得到过验证
        
#### 3.使用 resources/META-INF/spring.factories配置文件
    配置需要扫描给spring容器管理的类, 所有依赖当前jar包的spring容器都会加载管理这些类, 类似 java spi
    参考: https://www.cnblogs.com/huanghzm/p/12217630.html
    
#### 4.RedisTemplate StringRedisTemplate 比较 
[RedisTemplate与StringRedisTemplate比较](https://blog.csdn.net/notsaltedfish/article/details/75948281)