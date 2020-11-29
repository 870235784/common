package com.tca.common.log.slf4j.log4j2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouan
 * @Date 2020/11/28
 */
public class Slf4jLogging {

    private final static Logger log = LoggerFactory.getLogger(Slf4jLogging.class);

    public static void main(String[] args) {
        log.info("log = {}", log);
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
