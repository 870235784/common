#### 1.spring-boot-starter-web默认使用spring-boot-starter-logging (jcl集成logback)
******

#### 2.配置不同包下的日志级别(默认会以当前配置文件log4j2.xml为准, 也可以直接通过修改application.yml文件修改配置)
    
    logging:
        level:
            root: INFO
            org.springframework: ERROR
******
      
#### 3.使用当前包时需要将spring-boot-starter-logging排除

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
******

#### 4.不能同时引入common-log-springboot-log4j2 和 common-log-springboot-logback, 否则启动会报错 stackoverflow
    1.参见: 
        [简书](https://www.jianshu.com/p/191a95ad0b89)
        [slf4j官网](http://www.slf4j.org/codes.html#multiple_bindings)
    2.slf4j多绑定问题
        SLF4J: Found binding in [jar:file:/E:/java_install/apache-maven-repository/org/apache/logging/log4j/log4j-slf4j-impl/2.11.2/log4j-slf4j-impl-2.11.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: Found binding in [jar:file:/E:/java_install/apache-maven-repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
        SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation
        解答：找到多个slf4j的实现, 即有多个StaticLoggerBinder.class, jvm会随机加载一个
    3.slf4j stackoverflow问题
        log jar分类:
            接口: commons-logging-api(jcl) java.util.logging(jul) slf4j log4j-api(包括log4j1和log4j2)
            实现: 
                logback(默认实现了slf4j)
                log4j
                log4j2
                commons-logging-impl
            接口到实现的适配器:
                slf4j-jcl(桥接 jcl接口和log4j2实现)
                slf4j-log4j12-impl(桥接 slf4j接口和log4j2实现)
            实现转化接口的适配器:
                jcl-over-slf4j(将已有的jcl的接口实现强转为slf4j的接口实现)
                jul-to-slf4j(将已有的jul的接口实现强转为slf4j的接口实现)
                log4j-to-slf4j(将已有的log4j2的强转为slf4j的接口实现) 
        问题解析: 不能同时引入common-log-springboot-log4j2 和 common-log-springboot-logback
            common-log-springboot-log4j2中有:
                log4j-core(log4j2实现)
                slf4j-log4j12-impl(桥接log4j2和slf4j)
                (即默认使用slf4j接口, log4j2实现)
            common-log-springboot-logging中有:
                slf4j-api(slf4j接口)
                logback(slf4j接口实现)
                log4j-to-slf4j(将已有的log4j2的实现强转为slf4j的接口实现)
                (即默认会将log4j2的实现转化为slf4j接口[如果存在], 再用logback的具体实现实现)
            此时出现了 slf4j-log4j12-impl 和  log4j-to-slf4j 两个jar包
            [The purpose of slf4j-log4j12 module is to delegate or redirect calls made to an SLF4J logger to log4j. 
            The purpose of the log4j-over-slf4j module is to redirect calls made to a log4j logger to SLF4J. 
            If SLF4J is bound withslf4j-log4j12.jar and log4j-over-slf4j.jar is also present on the class path, a Stack-
            OverflowError will inevitably occur immediately after the first invocation of an SLF4J or a log4j logger.]
         
                       