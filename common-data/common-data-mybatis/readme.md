#### 1.配置输出日志
```yml
#mybatis日志，显示具体的SQL语句放在application.properties中
mybatis-plus:
    configuration:
        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```