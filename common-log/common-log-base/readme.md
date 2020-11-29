1.具体的日志实现框架
    log4j log4j2 logback ...

2.JCL (Jakarta commons logging)
    2.1 简介
        Jakarta Commons Logging (JCL)提供的是一个日志接口,
        同时兼顾轻量级和不依赖于具体的日志实现工具。它提供给中间件/日志工具开发者一个简单的日志操作抽象, 允许程序开发人员
        使用不同的具体日志实现工具
    2.2 原理
        当commons-logging.jar被加入到CLASSPATH之后, 它会合理地猜测你想用的日志工具, 然后进行自我设置, 用户根本不需要做
        任何设置。默认的LogFactory是按照下列的步骤去发现并决定那个日志工具将被使用(寻找过程会在找到第一个工具时中止):
            1.寻找当前factory中名叫org.apache.commons.logging.Log配置属性的值
            2.寻找系统中属性中名叫org.apache.commons.logging.Log的值
            3.如果应用程序的classpath中有log4j, 则使用相关的包装类-Log4JLogger
            4.如果应用程序运行在jdk1.4的系统中，使用相关的包装类-Jdk14Logger
            5.使用简易日志包装类SimpleLog
    2.3 使用
        导入log4j-jcl, 再导入具体的日志实现框架 如 log4j log4j2等, 并配置对应的日志文件即可
            <dependency>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-core</artifactId>
                <version>2.3</version>
            </dependency>
            <dependency>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-api</artifactId>
                <version>2.3</version>
            </dependency>
            <dependency>
                <groupId>org.apache.logging.log4j</groupId>
                <artifactId>log4j-jcl</artifactId>
                <version>2.3</version>
            </dependency>
        
3.slf4j
    3.1 简介
        slf4j跟JCL类似, 也是提供了一个日志接口, slf4j只做两件事:
            1.提供日志接口
            2.提供获取具体日志对象的方法
    3.2 jcl-over-slf4j
        当jar同时依赖了jcl日志体系和slf4j日志体系时, 需要引入jcl-over-slf4j, 将jcl体系适配到slf4j体系
  
