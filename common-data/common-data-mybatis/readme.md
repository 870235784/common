## 1.配置输出日志
#### 方式一 使用mybatis自带
```yml
# 使用mybatis自带的方式, 配置mapper所在包
logging:
  level:
    root: ERROR
    org.springframework: ERROR
    com.tca.common.data.mybatis.test.mapper: DEBUG
```

#### 方式二 使用mybatis-plus性能分析bean
```java
@Bean
public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    performanceInterceptor.setWriteInLog(true);
    performanceInterceptor.setFormat(true);
    return performanceInterceptor;
}
```


## 2.配置乐观锁
```
https://mp.baomidou.com/guide/interceptor-optimistic-locker.html#optimisticlockerinnerinterceptor
```

## 3.分页插件
```
https://mp.baomidou.com/guide/page.html
```

## 4.配置逻辑删除
```
https://mp.baomidou.com/guide/logic-delete.html
```

## 5.自动填充
```
https://mp.baomidou.com/guide/auto-fill-metainfo.html
```
#### 使用场景
1.自动填充 create_time 和 update_time
