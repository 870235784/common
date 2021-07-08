package com.tca.common.data.mybatis.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoua
 * @Date 2021/7/8
 */
@SpringBootApplication
@MapperScan("com.tca.common.data.mybatis.test.mapper")
public class MybatisTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisTestApplication.class, args);
    }


}
