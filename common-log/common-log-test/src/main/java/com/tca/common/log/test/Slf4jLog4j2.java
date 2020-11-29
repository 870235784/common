package com.tca.common.log.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouan
 * @Date 2020/11/28
 */
public class Slf4jLog4j2 {

    private final static Logger log = LoggerFactory.getLogger(Slf4jLog4j2.class);

    public static void main(String[] args) {
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
    }
}
